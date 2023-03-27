package de.aschoenberg.logic.dbHandling;

/**
 * Diese Klasse dient dazu alle
 * benoetigten SQL-Schluesselwoerter
 * gekapselt und zentral zur Verfuegung zu
 * stellen
 * <p>
 * (Wiederverwendung mit freundlicher Genemigung von Rudolf Histel)
 * Created by rHistel on 04.10.2017.
 */

public abstract class ASqlKeyWords {
	
	//region Datatypes
	protected static final String DATA_TYPE_INTEGER           = " INTEGER ";
	protected static final String DATA_TYPE_INTEGER_INC_COMMA = " INTEGER, ";
	
	protected static final String DATA_TYPE_REAL           = " REAL ";
	protected static final String DATA_TYPE_REAL_INC_COMMA = " REAL, ";
	
	protected static final String DATA_TYPE_TEXT_INC_COMMA = " TEXT, ";
	protected static final String DATA_TYPE_TEXT           = " TEXT ";
	
	protected static final int DATA_TYPE_BOOLEAN_FALSE = 0;
	protected static final int DATA_TYPE_BOOLEAN_TRUE  = 1;
	
	//endregion
	
	//region table actions
	protected static final String CREATE_TBL            = "CREATE TABLE ";
	protected static final String SELECT_TBL            = "SELECT * FROM ";
	protected static final String SELECT_COUNT_FROM_TBL = "SELECT COUNT(*) FROM ";
	protected static final String DROP_TBL              = "DROP TABLE IF EXISTS ";
	
	protected static final String INSERT_TBL      = "INSERT INTO ";
	protected static final String UPDATE_TBL      = "UPDATE ";
	protected static final String DELETE_FROM_TBL = "DELETE FROM ";
	//endregion
	
	//region keys
	protected static final String PRIMARY_KEY_AUTO_INCREMENT_INC_COMMA =
			DATA_TYPE_INTEGER + "PRIMARY KEY AUTOINCREMENT, ";
	protected static final String PRIMARY_KEY_INC_COMMA                = DATA_TYPE_INTEGER + "PRIMARY KEY, ";
	//endregion
	
	//region chars
	protected static final String CHAR_COMMA                   = ", ";
	protected static final String CHAR_COL_BACK_TICK           = "`";
	protected static final String CHAR_VALUE_BACK_TICK         = "\'";
	protected static final String CHAR_SEMICOLON               = ";";
	protected static final String CHAR_OPEN_BRACKET            = "(";
	protected static final String CHAR_CLOSE_BRACKET           = ")";
	protected static final String CHAR_PLACE_HOLDER            = "?";
	protected static final String CHAR_CLOSE_BRACKET_SEMICOLON = CHAR_CLOSE_BRACKET + CHAR_SEMICOLON;
	//endregion
	
	//region operators
	protected static final String SET_OPERATOR                     = " SET ";
	protected static final String AND_OPERATOR                     = " AND ";
	protected static final String VALUES_OPERATOR                  = " VALUES ";
	protected static final String EQUALS_OPERATOR                  = " = ";
	protected static final String EQUALS_OPERATOR_INC_PLACE_HOLDER = EQUALS_OPERATOR + CHAR_PLACE_HOLDER;
	//endregion
	
	//region conditions
	protected static final String WHERE_CONDITION    = " WHERE ";
	protected static final String ORDER_BY_CONDITION = " ORDER BY ";
	
	//endregion
}
