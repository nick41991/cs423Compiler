# Compiler - CSE 423 - Spring 2020
## Contributors
- Nicholas Smith
- Daniel Aranda
- Ty Darnell
- Alden Towler

## Program Overview

For our compiler we decided to use Java because the Java Virtual Machine (JVM) allows for the program to be widely used, as well as the language was widely understood by our team members. We also used the JavaCC parser generator in order to help us generate our parser. The program offers a very understandable way to implement our generated grammar. Both of these factors should make it easier to develop the compiler and allow for ease of use by users. The compiler will be usable from the command line of Linux systems.

Compiler.java controls the main logic of the Compiler. Compiler.jj handles all logic in regards to scannning and parsing. SymbolTable.java handles all logic in regards to the SymbolTable.

## Usage

#### Required software
- JDK 11

#### To use the compiler:

    $ java Compiler [flags] [.c file path]

#### To use -f option

    $ java Compiler -f [.c file path] [output file path]

#### To use -r option

    $ java Compiler -r [IR file]

#### Compiler Options

| Flags | Operation | Supported |
|-------|-----------|-----------|
| -t    | Output a token list |:heavy_check_mark:|
| -pt   | Output a parse tree |:heavy_check_mark:|
| -s    | Output symbol table |:heavy_check_mark:|
| -ir   | Output IR|:x:|
| -f    | Output IR to a specified file|:x:|
| -r    | Read in IR instead of source file|:x:|


## Design Discussion

For the parser we read in character by character, ignoring whitespaces, till we recognize a token. As we take in tokens we add them to a call stack as the program looks at the parameters of that token in the grammar. The parser then looks ahead at other tokens to see if they meet these grammar rules. This process continues until we hit a base case, where no other inputs are needed. The parser then works back up the call stack building the tree incorporating in the grammar specifications. This system is simple to produce and add to, but comes with the trade of un-optimized, as the parser must search recursively through the whole program. We expect a big O(n), with a linear growth rate. This can be seen as a limitation but the capacity for expansion we think makes this limitation worth it.

## Language Specification
Currently we recognize:

#### Required Language Features

|Features|Scanner|Parser|IR|
|--------|-------|------|--|
|Identifiers|:heavy_check_mark:|:heavy_check_mark:|:x:|
|Variables|:heavy_check_mark:|:heavy_check_mark:|:x:|
|Functions|:heavy_check_mark:|:heavy_check_mark:|:x:|
|Keywords|:heavy_check_mark:|:heavy_check_mark:|:x:|
|Arithmetic Expressions|:heavy_check_mark:|:heavy_check_mark:|:x:|
|Assignment|:heavy_check_mark:|:heavy_check_mark:|:x:|
|Boolean Expressions|:heavy_check_mark:|:heavy_check_mark:|:x:|
|GOTO Statements|:heavy_check_mark:|:heavy_check_mark:|:x:|
|If/Else Control Flow|:heavy_check_mark:|:heavy_check_mark:|:x:|
|Unary Operators|:heavy_check_mark:|:heavy_check_mark:|:x:|
|Return Statements|:heavy_check_mark:|:heavy_check_mark:|:x:|
|Break Statements|:heavy_check_mark:|:heavy_check_mark:|:x:|
|While Loops|:heavy_check_mark:|:heavy_check_mark:|:x:|

#### Optional Language Features

|Features|Scanner|Parser|IR|
|--------|-------|------|--|
|++|:x:|:x:|:x:|
|--|:x:|:x:|:x:|
|-=|:heavy_check_mark:|:heavy_check_mark:|:x:|
|+=|:heavy_check_mark:|:heavy_check_mark:|:x:|
|*=|:heavy_check_mark:|:heavy_check_mark:|:x:|
|/=|:heavy_check_mark:|:heavy_check_mark:|:x:|
|>|:heavy_check_mark:|:heavy_check_mark:|:x:|
|<|:heavy_check_mark:|:heavy_check_mark:|:x:|
|>=|:heavy_check_mark:|:heavy_check_mark:|:x:|
|<=|:heavy_check_mark:|:heavy_check_mark:|:x:|
|==|:heavy_check_mark:|:heavy_check_mark:|:x:|
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
