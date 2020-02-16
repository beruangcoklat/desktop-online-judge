package utility;

import java.io.IOException;

public class CompilerProxy {

	private Compiler compiler = new Compiler();
	private static CompilerProxy instance = new CompilerProxy();

	private CompilerProxy() {

	}

	public static CompilerProxy getInstance() {
		return instance;
	}

	public void setup(String codingan, String inputan){
		compiler.setCodingan(codingan);
		compiler.setInputan(inputan);
	}
	
	public String[] compile() {
		try {
			return compiler.compile();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
