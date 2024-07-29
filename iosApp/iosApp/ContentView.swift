import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greet()
    var addNore : String {
        return greet + "    HAHAHAHAH"
    }
    
	var body: some View {
		Text(greet)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
