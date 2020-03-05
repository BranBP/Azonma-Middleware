package com.azonma.util;

public class QueryUtils {

	public static final boolean addClause(Object parameter, StringBuilder queryString, boolean first, String clause) {
		return addClause(parameter!=null, queryString, first, clause);
	}

	public static final boolean addClause(boolean filterBy, StringBuilder queryString, boolean first, String clause) {
		if (filterBy) {
			addClause(queryString, first, clause);
			first = false;
		}
		return first;			
	}
	
	public static final void addClause(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first? "WHERE": "AND").append(clause);
	}
}
