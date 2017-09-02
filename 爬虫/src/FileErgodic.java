import java.io.File;
import java.io.FilenameFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileErgodic {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File file=new File("E:\\java");
		FileFilter filter = new FileNameExtensionFilter("java文件","java","java");
		List<File> listFiles = listFiles(file);
		for(File f : listFiles){
			System.out.println(f);
		}
		String[] list = file.list();
		for(String x:list){
			if(x.contains(".")){
				System.out.println(x);
			}
		}
	}
	/**
	 * <pre>
	 * 递归的列出文件
	 * 
	 * fileFilter ex.
	 * fileFilter = new FileFilter() {
	 *       public boolean accept(File pathname) {
	 *           return !".svn".equals(pathname.getAbsolutePath());
	 *       }
	 *  };
	 * </pre>
	 * 
	 * @param file
	 * @param recusive
	 * @param filter
	 * @return
	 * @throws java.io.IOException
	 */
	public static List<File> listFiles(File file) 
	        throws IOException {
	    List<File> fileList = new ArrayList<File>();
	    if (file.isDirectory()) {
	        Stack<File> stack = new Stack<File>();
	        stack.push(file);
	        File[] files;
	        
	        while (!stack.isEmpty()) {
	            files = stack.pop().listFiles();
	            for (File _file : files) {
	                if (_file.isDirectory()) {
	                    stack.push(_file);
	                } else if (_file.isFile()) {
	                    fileList.add(_file);
	                }
	            }
	        }
	    } else {
	        if (file.exists()) {
	            fileList.add(file);
	        }
	    }
	    return fileList;
	}
}
