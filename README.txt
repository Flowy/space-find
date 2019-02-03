To build run in home directory:
mvn clean package
java -jar target/space-find-1.0 [path to input]

Path to output file will be printed in console in the end

Use UTF-8 for encoding of files
Input:
expected order is room definition, then furniture definition/s
use empty line between room and furnitures

** Application expects that 'origin' of furniture is inside dimensions of room (origin of furniture should be most upper, most left position used by furniture)