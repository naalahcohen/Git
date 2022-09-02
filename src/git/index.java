package git;
import java.util.*;
import java.io.*;


public class Index {
	
	private File indexFile;
	private HashMap<String,String> indexMap = new HashMap<String,String>(); //map of the name(txt) and the sha1 of the contents 
	
	
	public Index() {
		
	}
	
	//makes an object folder, index file 
	public void init() throws IOException {
		File f = new File("Test/Objects");
		f.mkdir();
		indexFile = new File("Test/index.txt");
		FileWriter writer = new FileWriter(indexFile);
	}
	
	public void add(String fileName) throws IOException {//add blob to objects folder, do the index with the fileName : hashedContents
		Blob b = new Blob("Test/" + fileName);//filename ex = foo.txt
//		Scanner txtScanner = new Scanner(fileName);
//		String str = "";
//		while (txtScanner.hasNextLine()) {
//			str+=txtScanner.nextLine();
//		}
//		String sha = Blob.getSha1(str);
		String sha = b.getHashed();
		indexMap.put(fileName, sha);
		Scanner s = new Scanner(indexFile);
		String alreadyIndexed = "";
		while (s.hasNextLine()) {
			alreadyIndexed+=s.nextLine();
		}
		FileWriter fw = new FileWriter(indexFile);
		fw.append(alreadyIndexed + "\n" + fileName + " : " + sha);
		
		fw.close();
	}
	
	public void remove(String fileName) throws IOException {
		int length = fileName.length();//=7
		String sha = indexMap.get(fileName);
		File f = new File("Test/Objects/" + sha + ".txt");
		f.delete();//correctly deletes actual file
		String whatsLeft = "";
		Scanner in = new Scanner(indexFile);
		while(in.hasNextLine()) {
			String newLine = in.nextLine();
			if (newLine.substring(0,length).equals(fileName)) {//if the first _ letters = fileName 
				
			}
			else {
				whatsLeft +=newLine + "\n";
			}
		}
		FileWriter fw = new FileWriter(indexFile);
		fw.append(whatsLeft);
		fw.close();
	}
	
	
	
}
