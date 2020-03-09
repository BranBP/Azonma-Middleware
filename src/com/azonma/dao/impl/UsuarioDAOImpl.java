package com.azonma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.azonma.dao.UsuarioDAO;
import com.azonma.exceptions.DataException;
import com.azonma.model.Estado;
import com.azonma.model.Usuario;
import com.azonma.model.criteria.UsuarioCriteria;
import com.azonma.util.JDBCUtils;
import com.azonma.util.PasswordEncryption;
import com.azonma.util.QueryUtils;
import com.mysql.jdbc.Statement;

public class UsuarioDAOImpl implements UsuarioDAO{

	private static Logger logger = LogManager.getLogger(UsuarioDAOImpl.class.getName());

	@Override
	public Usuario findById(Connection connection, long id) throws DataException { 

		Usuario usuario = new Usuario();
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder(
					"SELECT U.ID_USUARIO, U.EMAIL, U.CONTRASENA, U.FECHA_NACIMIENTO, U.NOMBRE, "
							+" U.APELLIDO1, U.APELLIDO2, U.ID_SEXO, U.ID_IDIOMA, E.NOMBRE FROM USUARIO U "
							+" INNER JOIN ESTADO E ON U.ID_ESTADO = E.ID_ESTADO "
					); 

			boolean first = true;

			first = QueryUtils.addClause(id, stringBuilder, first, " U.ID_USUARIO = ? ");

			query = stringBuilder.toString();
			preparedStatement = connection.prepareStatement(query);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int i = 1;
			preparedStatement.setLong(i++, id);

			rs = preparedStatement.executeQuery();

			if(rs.next()) {
				usuario = loadNext(rs); 
			}

		} catch (SQLException e) {
			logger.error("Error. idUsuario: {}", id);  
		} 

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		} 

		return usuario; 
	}

	@Override
	public List<Usuario> findByCriteria(Connection cn, UsuarioCriteria uc) throws DataException {  

		List <Usuario> usuarios = new ArrayList<Usuario>(); 
		PreparedStatement preparedStatement = null;
		String query = null;
		ResultSet rs = null;

		try {

			StringBuilder stringBuilder = new StringBuilder(" SELECT U.ID_USUARIO, U.EMAIL, U.CONTRASENA, U.FECHA_NACIMIENTO,"
					+" U.NOMBRE, U.APELLIDO1, U.APELLIDO2, U.ID_SEXO, U.ID_IDIOMA, E.NOMBRE "
					+" FROM USUARIO U INNER JOIN IDIOMA I ON U.ID_IDIOMA = I.ID_IDIOMA "
					+" INNER JOIN ESTADO E ON U.ID_ESTADO = E.ID_ESTADO ");

			boolean first = true;

			if (uc.getEmail() != null) {
				first = QueryUtils.addClause(uc.getEmail(), stringBuilder, first, " U.EMAIL LIKE ? ");
			}

			if (uc.getFechaNacimiento() != null) {
				first = QueryUtils.addClause(uc.getFechaNacimiento(), stringBuilder, first, " U.FECHA_NACIMIENTO >= ? ");
			}

			if(uc.getNombre() != null) {
				first = QueryUtils.addClause(uc.getNombre(), stringBuilder, first, " UPPER(U.NOMBRE) LIKE ? ");
			}

			if(uc.getApellido1() != null) {
				first = QueryUtils.addClause(uc.getApellido1(), stringBuilder, first, " UPPER(U.APELLIDO1) LIKE ? ");
			}

			if(uc.getApellido2() != null) {
				first = QueryUtils.addClause(uc.getApellido2(), stringBuilder, first, " UPPER(U.APELLIDO2) LIKE ? ");
			}

			if(uc.getSexo() != null) {
				first = QueryUtils.addClause(uc.getSexo(), stringBuilder, first, " UPPER(U.ID_SEXO) LIKE ? ");
			}

			if(uc.getIdioma() != null) {
				first = QueryUtils.addClause(uc.getIdioma(), stringBuilder, first, " UPPER(I.NOMBRE) LIKE ? ");
			}
			 
			if(uc.getEstado()!=null) {
				first = QueryUtils.addClause(uc.getEstado(), stringBuilder, first, " UPPER(E.NOMBRE) LIKE ? ");
			}

			query = stringBuilder.toString();
			preparedStatement = cn.prepareStatement(query);
			int i = 1;

			if(uc.getEmail() != null) {
				preparedStatement.setString(i++, uc.getEmail()); 
			}

			if(uc.getFechaNacimiento() != null) {
				preparedStatement.setDate(i++, new java.sql.Date(uc.getFechaNacimiento().getTime()));
			}

			if(uc.getNombre() != null) {
				preparedStatement.setString(i++, "%" + uc.getNombre() + "%");		 	 	
			}

			if(uc.getApellido1() != null) {
				preparedStatement.setString(i++, "%" + uc.getApellido1() + "%");
			}

			if(uc.getApellido2() != null) {
				preparedStatement.setString(i++, "%" + uc.getApellido2() + "%"); 
			}

			if(uc.getSexo() != null) {
				preparedStatement.setString(i++, uc.getSexo()); 
			}

			if(uc.getIdioma() != null) {
				preparedStatement.setString(i++, "%" + uc.getIdioma() + "%"); 
			}

			if(uc.getEstado() != null) {
				preparedStatement.setString(i++, "%" + uc.getEstado() + "%");  
			}

			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Usuario r = new Usuario();
				r = loadNext(rs); 
				usuarios.add(r);
			} 

			if(usuarios.size() == 0) {
				logger.warn("Criterios no encontrados"); 
			}else {
				logger.info("Han salido {} resultados", usuarios.size()); 
			} 

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

		} catch (SQLException e) {
			logger.error("Error. Usuario: {}", uc);  
		}

		finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		} 
		return usuarios;
	}

	@Override
	public Usuario create(Connection cn, Usuario u) throws DataException { 

		PreparedStatement preparedStatement = null; 
		String query = null;
		ResultSet rs = null;

		try {

			query = " INSERT INTO USUARIO (EMAIL, CONTRASENA, FECHA_NACIMIENTO, NOMBRE, APELLIDO1, APELLIDO2, ID_SEXO, ID_IDIOMA, ID_ESTADO)"
					+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, "+Estado.CREADO+")"; 

			preparedStatement = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int i = 1;
			preparedStatement.setString(i++, u.getEmail()); 
			preparedStatement.setString(i++, PasswordEncryption.encryptPassword(u.getContrasena())); 
			preparedStatement.setDate(i++, new java.sql.Date(u.getFechaNacimiento().getTime())); 
			preparedStatement.setString(i++, u.getNombre()); 
			preparedStatement.setString(i++, u.getApellido1()); 

			if(u.getApellido2()!=null) {
				preparedStatement.setString(i++, u.getApellido2()); 
			}else {
				preparedStatement.setNull(i++, java.sql.Types.VARCHAR);   
			}

			preparedStatement.setString(i++, u.getIdSexo()); 
			preparedStatement.setLong(i++, u.getIdIdioma()); 
			
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Usuario'");
			}

			rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				Long id = rs.getLong(1);  
				u.setId(id); 
			}

		} catch (SQLException e) {
			logger.error("Error: {}", preparedStatement.toString());  
		} finally {            
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return u; 
	}

	@Override
	public void update(Connection cn, Usuario u, long id) throws DataException {    

		PreparedStatement preparedStatement = null;
		String query = null;

		try {

			query = " UPDATE USUARIO SET EMAIL = ?, CONTRASENA = ?, FECHA_NACIMIENTO = ?, NOMBRE = ?, APELLIDO1 = ?,"
					+" APELLIDO2 = ?, ID_SEXO = ?, ID_IDIOMA = ? WHERE ID_USUARIO = " +id; 

			preparedStatement = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); 

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int i = 1;
			preparedStatement.setString(i++, u.getEmail()); 
			preparedStatement.setString(i++, PasswordEncryption.encryptPassword(u.getContrasena())); 
			preparedStatement.setDate(i++, new java.sql.Date(u.getFechaNacimiento().getTime())); 
			preparedStatement.setString(i++, u.getNombre()); 
			preparedStatement.setString(i++, u.getApellido1()); 

			if(u.getApellido2()!=null) {
				preparedStatement.setString(i++, u.getApellido2()); 
			}else {
				preparedStatement.setNull(i++, java.sql.Types.VARCHAR);   
			}

			preparedStatement.setString(i++, u.getIdSexo()); 
			preparedStatement.setLong(i++, u.getIdIdioma()); 

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Usuario'");
			}

		} catch (Exception e) {
			logger.error("Error: {}", preparedStatement.toString()); 
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override 
	public void updateEstado(Connection cn, long id, int idEstado) throws DataException { 

		PreparedStatement preparedStatement = null;
		String query = null;

		try {

			query = " UPDATE USUARIO SET ID_ESTADO = " + idEstado + " WHERE ID_PEDIDO = " + id; 

			preparedStatement = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			if(logger.isDebugEnabled()) {
				logger.debug("Query: {} ", query);
			}

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Pedido'");
			}

		}catch (SQLException e) {
			logger.error("Error. idUsuario: {}, idEstado: {}", id, idEstado);  
		}

		finally {            
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public void delete(Connection connection, long id) throws DataException {
		this.updateEstado(connection, id, Estado.BORRADO);  
	}

	public Usuario loadNext(ResultSet rs) throws SQLException, DataException {

		Usuario r = new Usuario(); 
		int i = 1;

		r.setId(rs.getLong(i++));
		r.setEmail(rs.getString(i++));
		r.setContrasena(rs.getString(i++));
		r.setFechaNacimiento(rs.getDate(i++));
		r.setNombre(rs.getString(i++));
		r.setApellido1(rs.getString(i++));
		r.setApellido2(rs.getString(i++));
		r.setIdSexo(rs.getString(i++)); 
		r.setIdIdioma(rs.getLong(i++));
		r.setEstado(rs.getString(i++));  

		return r;
	}

}