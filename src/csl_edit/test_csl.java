package csl_edit;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import de.undercouch.citeproc.CSL;
//import de.undercouch.citeproc.output.Bibliography;
//import de.undercouch.citeproc.output.Citation;
//import java.util.List;

import org.jbibtex.ParseException;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

import de.undercouch.citeproc.bibtex.BibTeXConverter;
import org.jbibtex.BibTeXDatabase;
import de.undercouch.citeproc.bibtex.BibTeXItemDataProvider;
//import de.undercouch.citeproc.csl.CSLCitation;

public class test_csl {

	/**
	 * @param path
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	static void writeFile(String fileName, String fileContent) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(fileContent);
		writer.close();
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		try {

			BibTeXDatabase db = new BibTeXConverter().loadDatabase(new FileInputStream(
					"/Users/marcus/Documents/eclipse_workspaces/csl_workspace/csl_edit/bibliographies/zotero_library_smaller.bib"));
			BibTeXItemDataProvider provider = new BibTeXItemDataProvider();
			provider.addDatabase(db);

			String file_content = readFile(
					// "/Users/marcus/Documents/eclipse_workspaces/csl_workspace/csl_edit/jahrbuch-fuer-wirtschaftsgeschichte.xml",
					"/Users/marcus/Documents/eclipse_workspaces/csl_workspace/csl_edit/citation-style/economic-history-yearbook.xml",
					StandardCharsets.UTF_8);
			
			String styleOutputFile = "/Users/marcus/Documents/eclipse_workspaces/csl_workspace/csl_edit/citation-style/output/economic-history-yearbook.csl";
			
			writeFile(styleOutputFile, file_content);
			
			System.out.println("Wrote style to " + styleOutputFile);
			
			CSL citeproc = new CSL(provider, file_content);

			citeproc.setOutputFormat("html");

			System.out.println(citeproc.makeCitation("earle_econocracy:_2017").get(0).getText());

			System.out.println(citeproc.makeCitation("mackenzie_constructing_2003").get(0).getText());
			System.out.println(citeproc.makeCitation("earle_econocracy:_2017").get(0).getText());

			System.out.println(citeproc.makeCitation("earle_econocracy:_2017").get(0).getText());
			System.out.println(citeproc.makeCitation("mackenzie_constructing_2003").get(0).getText());
			System.out.println(citeproc.makeCitation("earle_econocracy:_2017").get(0).getText());

			System.out.println(citeproc.makeCitation("thatcher_supranational_2013").get(0).getText());

//			CSLCitation test = new cslci
//			citeproc.makeCitation(citation)
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
