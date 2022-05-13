fun main() {
	var angka_pertama = 1
	var angka_terakhir = 1000
	var jumlah_pembagi = 0
    
    for(angka in angka_pertama..angka_terakhir){
    	for(pembagi in 1..angka){
        	if(angka%pembagi==0){
            	jumlah_pembagi++
        	}
    	}
    	if(angka != 1 && jumlah_pembagi<=2){
        	println(angka)
    	}
    	jumlah_pembagi=0
	}
}
