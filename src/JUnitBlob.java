import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.zip.GZIPOutputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import git.Blob;
import git.index;

class JUnitBlob {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		FileUtil.writeStringToFile("naalah.txt");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		FileUtil.deleteStringToFile("naalah.txt");
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	

	@Test
	void testingFileContents() throws IOException {
		Blob bnew = new Blob ("naalah.txt");
		String sha1 = bnew.getSha1("hi poopie pants ");
		System.out.println(sha1); 
		assertTrue(sha1.equalsIgnoreCase("8CB1F7AD8A6EA433BC04A3B374813448448D8740"));
	}

	@Test
	void testingifIndexAndObjectsExists () throws IOException {
		index indexie = new index ();
		indexie.init();
		
		File file = new File ("Test/index.txt");
		assertTrue(file.exists());
		
		Path p = Paths.get("Test/Objects"); 
		assertTrue(Files.exists(p)); 
	}
	
//	@Test
//	void testAdd () throws IOException {
//		index i = new index();
//		i.init();
//		i.add("naalah.txt");
//		
//
//		File file = new File("index.txt");
//
//		InputStreamReader streamReader = new InputStreamReader(new FileInputStream(file));
//		  
//		BufferedReader br = new BufferedReader(streamReader);
//
//		String line = new String();
//		System.out.println(file.getName());
//		System.out.println("================");
//		while (br.ready()) {
//		line = br.readLine();
//		}
//		System.out.println("\n================");
//		System.out.println("Last line of the file is : ");
//		System.out.println(line);
//		
//		assertTrue(line.equalsIgnoreCase("naalah.txt : f47183f13b8b1ed1c7188b75563bfec93521e590"));
//		
//		
//		}
	
	@Test
	void testingIndexDelete() throws IOException {
		index i = new index();
		i.init();
		i.add("naalah.txt");
		i.remove("naalah.txt");
		Path printie = Paths.get("Test/index.txt");
		String content2 = Files.readString(printie);
		String nameOfFile = content2.substring(53,59); 
		String hashOfFile = content2.substring(67,74); 
	    assertFalse(nameOfFile.equals("naalah") && hashOfFile.equals("f47183"));
	  }
	
	@Test
	void testingIndexAdd() throws IOException {
		index i = new index();
		i.init();
		i.add("naalah.txt");
		Path printie = Paths.get("Test/index.txt");
		String content2 = Files.readString(printie);
		String nameOfFile = content2.substring(53,59); 
		String hashOfFile = content2.substring(67,74); 
	    assertTrue(nameOfFile.equals("naalah") && hashOfFile.equals("f47183"));
	  }
		
	
	@Test
	void testingFileLocationandName () throws IOException{
		Blob bnew = new Blob ("naalah.txt");
		Path p = Paths.get("f47183f13b8b1ed1c7188b75563bfec93521e590.txt"); 
		assertTrue(p.toAbsolutePath().equals("/Users/loaner/eclipse-workspace/Git/Test/Objects"));
	} 
//	
//	@Test
//	void testingUnZip () throws IOException {
//		String str = FileUtil.zipUtil("ÿËÈT(ÈÏ/ÈLU(HÌ+)V  ¢í5      "); 
//		System.out.println (); 
//	}
//	
//	
//	@Test 
//	void testingIfIndexAdds () throws IOException {
//		index indexie = new index ();
//		indexie.init();
//		
//		indexie.add("naalah.txt");
//		File file = new File ("Objects")
//		
//	}
//	
////
//	@Test 
//	void testingifInObjectsFolderandIfExists () throws IOException{
//		Blob blobbie = new Blob ("test.txt");
//		
//		File f = new File("./objects/adc83b19e793491b1c6ea0fd8b46cd9f32e592fc");
//		assertTrue(f.exists()); 
//	}
//	
//	@Test
//	void testingIfObjectsDirectoryExists() throws IOException {
//		Blob blobbie = new Blob ("test.txt");
//
//		File file = new File ("index"); 
//		assertTrue(file.exists()); 
//		
//		
//		Path p = Paths.get("objects");
//		assertTrue(Files.exists(p));
//	}
//	
//	@Test 
//	void testingIfIndexExists() throws IOException {
//		index indexie = new index (); 
//		indexie.init(); 
//		assertTrue();
//	}
//	
//	File getFile () {
//		return 
//	}
//	
//	
//	@Test
//	void testingifIndexIsCorrectAfter () throws IOException {
//		index indexie = new index (); 
//		indexie.init(); 
//		FileUtil.writeStringToFile("Test/index.txt");
//		
//		Blob blobbie = new Blob ("naalah.txt"); 
//		
//	}
//	
//	
	
	

	
	

}
