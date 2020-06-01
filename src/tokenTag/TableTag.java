package tokenTag;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class TableTag extends TokenTag {
	Style def = StyleContext.getDefaultStyleContext().
            getStyle(StyleContext.DEFAULT_STYLE);
	Style s = doc.addStyle("table", def);
	public TableTag(StyledDocument target, String start, String end) {
		super(target, start, end);
		
	}
	
	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		int isTable = 1, num1 = 0, num2 = 0, linenum = 0, subnum = 0;
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1), j=str.indexOf(this.endToken, i+1)) {
			for(int k=i; k<=str.indexOf("\n", i+1); ++k) { //title line
				if(str.substring(k, k+1).equals("|")) {
					num1++;
				}
			}
			for(int k=str.indexOf("\n", i+1)+1; k<=j+1; ++k) { //table line
				if(str.substring(k, k+1).equals("|")) {
					num2++;
				}
				if(str.substring(k, k+1).equals("|") == false && str.substring(k, k+1).equals("-") == false) {
					isTable = 0;
				}
			}
			if(num1 != num2) {
				isTable = 0;
				continue;
			}
			if(isTable == 1) {
				for(int k=str.indexOf("|\n", j+3); k!=-1; k=str.indexOf("|\n", k+1)) {
					linenum++;
				}
				String[] columnNames = new String[num1-1];
				String[][] rowData = new String[linenum][num1-1];
				int tmp1 = 0, tmp2 = 0, tmp3 = 0;
				for(int k=i, n=str.indexOf("|", k+1);
						n<=str.indexOf("\n", i+1) && k!=-1 && n!=-1; 
						k=n, n=str.indexOf("|", k+1)) {
					columnNames[tmp1] = str.substring(k+1, n);
					doc.setCharacterAttributes(k+1, n-k-1, this.doc.getStyle("table"), true);
					//doc.remove(k, 1);
					//doc.remove(n, 1);
					//subnum += 2;
					System.out.println("k="+k);
					System.out.println("n="+n);
					System.out.println("columnNames[tmp1]="+columnNames[tmp1]);
					tmp1++;
				}
				for(int p=str.indexOf("|\n", j), k=str.indexOf("|\n", j+3); 
						p!=-1 && k!=-1; 
						p=k, k=str.indexOf("|\n", p+1)) {
					tmp3 = 0;
					for(int m=str.indexOf("|", p+1), n=str.indexOf("|", m+1);
							n<=k && m!=-1 && n!=-1; 
							m=n, n=str.indexOf("|", m+1)) {
						System.out.println("k="+k);
						System.out.println("n="+n);
						System.out.println("m="+m);
						rowData[tmp2][tmp3] = str.substring(m+1, n);
						doc.setCharacterAttributes(m+1, n-m-1, this.doc.getStyle("table"), true);
						//doc.remove(m, 1);
						//doc.remove(n, 1);
						//subnum += 2;
						System.out.println("rowData[tmp2][tmp3]="+rowData[tmp2][tmp3]);
						tmp3++;
					}
					tmp2++;
				}
				 JTable jt=new JTable(rowData,columnNames);
				 jt.setPreferredScrollableViewportSize(jt.getPreferredSize());
				 JScrollPane jscrollpane = new JScrollPane(jt);
				 StyleConstants.setComponent(s, jscrollpane);
			}
			
			/*doc.setCharacterAttributes(i, j-i+1, this.doc.getStyle("table"), true);
			doc.remove(i, this.startToken.length());
			if(this.endToken.equals("\n") == false) 
				doc.remove(j-this.startToken.length(), this.endToken.length());*/
			str = this.doc.getText(0, this.doc.getLength());
		}
	}
}
