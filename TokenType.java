public enum TokenType {
    ID, 			// Identifiers, variables, functions, Labels (may have digits after at least one character)
    NUM_CONSTANT, 	// 0, 1, 2, .. , 10, .., etc (digits only)
    TYPE_SPEC,		// int, void
    CONTROL, 		// else, if, return, while, goto, break
    MUL_OP,			// * , /
    ADD_OP,			// +, -
    UNARY_OP,		// !, (++, --, +=, -=, *=, /= are optional)
    REL_OP,			// <, <=, >, >=, ==, !=
    ASSIGN_OP,		// =, : (colon assigns a label to a section of code)
    LEFT_PAREN,		// (
    RIGHT_PAREN,	// )
    LEFT_SQ_BRACKET,	// [
    RIGHT_SQ_BRACKET,	// ]
    LEFT_CURL_BRACE, 	// {
    RIGHT_CURL_BRACE,	// }
    COMMA,			// ,
    SEMI_COLON		// ;
}
