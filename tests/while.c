
int my_func(int a){
	return a;
}

int main(){
	int a;
	a = 1;
LABEL:
	while(!(a > 10)){
		a = a + 1;
		if ((a % 1) == 0){
			break;		
		}
	}

	if( (a > 10) && (a == 5) || (my_func(a) == a))
		a = a - 1;
		
	else {
		a = a;
		goto LABEL;	
	}
	int b;
	a = my_func(a);
	
	return a;
}
