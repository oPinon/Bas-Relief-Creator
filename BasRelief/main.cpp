#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main() {

ifstream file("suzan.obj");
string content;

//while(file >> content) {
for(int i=0; i<50; i++) {
        file >> content;
        cout << content << '\n';
    }
    return 0;
}
