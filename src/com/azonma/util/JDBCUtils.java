package com.azonma.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.azonma.exceptions.DataException;

public final class JDBCUtils {

	private JDBCUtils() {}

	/**
	 * Cierra el ResultSet.
	 */
	public static void closeResultSet(ResultSet resultSet)
			throws DataException {

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DataException(e);
			}
		}

	}

	/**
	 * Cierra el Statement.
	 */
	public static void closeStatement(Statement statement)
			throws DataException {

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DataException(e);
			}
		}

	}

	/**
	 * Cierra la conexion.
	 * 
	 * Metodo de cierre <b>para el caso de sentencias con autocommit = true</b>.
	 * 
	 * En caso de autocommit = false debería usarse: 
	 * {@link #closeConnection(Connection, Boolean)} 
	 * para que ejecute previamente commit() o rollback() 
	 * previamente al cierre.
	 * 
	 * @param connection Conexion a cerrar.
	 */
	public static void closeConnection(Connection connection)
			throws DataException {

		if (connection != null) {
			try {
				// Previene un mal uso 
				if (!connection.getAutoCommit()) {
					throw new DataException("Autocommit disabled. Commit or Rollback should be done explicitly.");
				}			

				connection.close();
			} catch (SQLException e) {
				throw new DataException(e);
			}
		}
	}

	/**
	 * Metodo de finalización de transacción <b>para el caso de autocommit = false</b>
	 * y de cierre de conexión.
	 *  
	 * Ejecuta commit() o rollback() y a continuacion cierra la conexión.
	 *  
	 * @param connection Conexión a cerrar.
	 * @param commitOrRollback Si es true ejecuta commit() y en caso contrario ejecuta rollback().
	 */
	public static void closeConnection(Connection connection, boolean commitOrRollback)
			throws DataException {
		try {
			if (connection != null) {
				if (commitOrRollback) {
					connection.commit();
				} else {
					connection.rollback();                        
				}
				connection.close();
			}
		} catch (SQLException e) {
			throw new DataException(e);
		}
	}

}