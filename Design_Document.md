# Compiler - CSE 423 - Spring 2020
## Contributors
- Nicholas Smith
- Daniel Aranda
- Ty Darnell
- Alden Towler

## Program Overview

For our compiler we decided to use Java because the Java Virtual Machine (JVM) allows for the program to be widely used, as well as the language was widely understood by our team members. We also used the JavaCC parser generator in order to help us generate our parser. The program offers a very understandable way to implement our generated grammar. Both of these factors should make it easier to develop the compiler and allow for ease of use by users. The compiler will be usable from the command line of Linux systems.

Compiler.java controls the main logic of the Compiler. Parser.jj handles all logic in regards to scannning and parsing. SymbolTable.java handles all logic in regards to the SymbolTable.

## Usage

#### Required software
- JDK 11

#### To use the compiler:

	$ javac Compiler.java

    $ java Compiler [flags] [.c file path]

#### To use -f option

    $ java Compiler -f [output file path] [.c file path] 

#### To use -r option

    $ java Compiler -r [IR file]

#### Compiler Options

| Flags | Operation | Supported |
|-------|-----------|-----------|
| -t    | Output a token list |:heavy_check_mark:|
| -pt   | Output a parse tree |:heavy_check_mark:|
| -s    | Output symbol table |:heavy_check_mark:|
| -ir   | Output IR|:heavy_check_mark:|
| -f    | Output IR to a specified file|:heavy_check_mark:|
| -r    | Read in IR instead of source file|:heavy_check_mark:|


## Design Discussion

For the parser we read in character by character, ignoring whitespaces, till we recognize a token. As we take in tokens we add them to a call stack as the program looks at the parameters of that token in the grammar. The parser then looks ahead at other tokens to see if they meet these grammar rules. This process continues until we hit a base case, where no other inputs are needed. The parser then works back up the call stack building the tree incorporating in the grammar specifications. This system is simple to produce and add to, but comes with the trade of un-optimized, as the parser must search recursively through the whole program. We expect a big O(n), with a linear growth rate. This can be seen as a limitation but the capacity for expansion we think makes this limitation worth it.

## Language Specification
Currently we recognize:

#### Required Language Features

|Features|Scanner|Parser|IR|
|--------|-------|------|--|
|Identifiers|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|Variables|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|Functions|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|Keywords|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|Arithmetic Expressions|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|Assignment|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|Boolean Expressions|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|GOTO Statements|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|If/Else Control Flow|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|Unary Operators|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|Return Statements|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|Break Statements|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|While Loops|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|

#### Optional Language Features

|Features|Scanner|Parser|IR|
|--------|-------|------|--|
|++|:x:|:x:|:x:|
|--|:x:|:x:|:x:|
|-=|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|+=|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|*=|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|/=|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|>|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|<|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|\|\||:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|&&|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|>=|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|<=|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|==|:heavy_check_mark:|:heavy_check_mark:|:heavy_check_mark:|
|Types Other</br>Than Integers|:x:|:x:|:x:|
|For Loops|:x:|:x:|:x:|
|Binary Operators|:x:|:x:|:x:|
|Switch Statements|:x:|:x:|:x:|
|Pointers, Arrays, Strings|:x:|:x:|:x:|
|Preprocessor Statements|:x:|:x:|:x:|
|Struct, Enum|:x:|:x:|:x:|
|Casting, Type Promotion|:x:|:x:|:x:|
|Type Specs|:x:|:x:|:x:|

## Intermediate Language Design
In designing our Intermediate representation, we aimed for simplicity mimicking the general structure of c code but without the indentations and identifiers.
 - The IR is essentially a list of strings, which each represent 1 instruction, function, or operation.
 - Functions are represented by their name and parameters and are followed by a compound statement:
 	main(a, b){}
 - Compound Statements are contained by pairs of parenthesis.
 - Compound Statements have a local declaration block and a statement list.
 - Our IR ignores local declarations because they are already contained within the symbol table.
 - Expressions are restructured in a three address form.
 - This requires the use of placeholders, represented in the IR by Lx (where x is a number).
 - They take the form of Lz = Lx + Ly. where Lx and Ly are also placeholders which may be a similar expression or a base case such as a variable or constant. For large expressions, this can form a long chain.
 - In generating the IR, a placeholder's chain must be followed, writing the base cases of the placeholder to the IR before it can be written to the IR. In the above example x and y would have to be resolved and expanded before z could.
 - Keywords with a mathematical or conditional expression such as return, if, and while appear as they normally would, followed by a placeholder representing their expression.
 - Many placeholders hold single numbers or variables currently. This can be optimized later with constant folding and propagation.

 For samples of the IR, please see files ending in "_IR_Sample" in the folder SampleOutputs
