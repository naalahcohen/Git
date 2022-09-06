package git;
import java.security.*;
import java.math.*;
import java.util.*;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Blob {
	
	private String contents;
	
	private String zippedContents;
	
	private String hashedContents;
	
	public String getHashed() {
		return hashedContents;
	}
	
	public Blob (String file) throws IOException {
		String ret = "";
		try {//takes in file, reads in to contents
		      File f = new File(file);
		      Scanner in = new Scanner(f);
		      while (in.hasNextLine()) {
		        ret+= in.nextLine();
		      }
		      in.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		contents = ret;//contents = what was read in
		//HERE WOULD BE: CONTENTS = GETZIP(CONTENTS);
		zippedContents = getZip(contents);
		System.out.println("ZIPPED: " + getZip(contents));
		hashedContents = getSha1(zippedContents);//call the hash method on the contents(now zipped contents), save it in hashedContents
		createFile();
		System.out.println(ret);
		System.out.println(hashedContents);
		System.out.println();
	}
	
	private void createFile () throws IOException{//Goal: create file in the objects folder with a certain name of the hashed contents 
		File f = new File("Test/Objects/" + hashedContents + ".txt");//DEPENDENT ON OBJECTS FOLDER NAME = OBJECTS 
		FileWriter writer = new FileWriter(f);
		writer.append(zippedContents);
		writer.close();
	}
	
	private  String getZip(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        return out.toString("ISO-8859-1");
    }
	
	public static String getSha1 (String input) {
		String value = input;
		String sha1 = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(value.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}

		return sha1;
	}
}
