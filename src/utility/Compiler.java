package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Compiler implements Runnable {

	public static final String COMPILER_ERROR = "1", TLE = "2", SUCCESS = "3", RUNTIME_ERROR = "4";
	private Process proc;
	private String codingan, inputan;
	private int timeLimit = 10000;

	public String[] compile() throws InterruptedException, IOException {
		writeFile();
		new Thread(this).start();
		proc = Runtime.getRuntime().exec("javac Main.java");
		proc.waitFor();

		String errorCompile = convert(proc.getErrorStream());
		if (errorCompile.length() != 0) {
			JOptionPane.showMessageDialog(null, errorCompile);
			return new String[] { COMPILER_ERROR, "???" };
		}

		proc = Runtime.getRuntime().exec("java Main");
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(proc.getOutputStream()));
		out.write(inputan);
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		String line;
		String ret = "";
		while ((line = in.readLine()) != null) {
			ret += line;
			ret += '\n';
		}

		in.close();
		proc.waitFor();

		String errorRun = convert(proc.getErrorStream());
		if (errorRun.length() != 0) {
			JOptionPane.showMessageDialog(null, errorRun);
			return new String[] { RUNTIME_ERROR, "???" };
		}

		if (proc.exitValue() == 1) {
			JOptionPane.showMessageDialog(null, "Time Limit Exceeded");
			return new String[] { TLE, "???" };
		}

		return new String[] { SUCCESS, ret };
	}

	@Override
	public void run() {
		Util.sleep(timeLimit);
		Util.sleep(1000);
		proc.destroy();
	}

	private void writeFile() {
		File file = new File("Main.java");
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.print(codingan);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String convert(InputStream is) {
		final int PKG_SIZE = 1024;
		byte[] data = new byte[PKG_SIZE];
		StringBuilder buffer = new StringBuilder(PKG_SIZE * 10);

		try {
			int size = is.read(data, 0, data.length);
			while (size > 0) {
				String str = new String(data, 0, size);
				buffer.append(str);
				size = is.read(data, 0, data.length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return buffer.toString().replaceAll("\r\n", "\n");
	}

	public void setCodingan(String codingan) {
		this.codingan = codingan;
	}

	public void setInputan(String inputan) {
		this.inputan = inputan;
	}

}
