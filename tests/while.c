int my_func(int a){
	return a;
}

int main(){
	int a;
	a = 1;
	while(a < 10){
		a = a + 1;
	}
	a = my_func();
	return a;
}
