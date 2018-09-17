import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Run extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private Panel1 panel1 = null;

	/**
	 * This method initializes panel1	
	 * 	
	 * @return Panel1	
	 */
	private Panel1 getPanel1() {
		if (panel1 == null) {
			panel1 = new Panel1();
			panel1.setLayout(new BorderLayout());
		}
		return panel1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Run thisClass = new Run();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public Run() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(new Dimension(800, 600));
		Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
		this.setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
		this.setContentPane(getJContentPane());
		this.setTitle("RapidCell 1.4");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanel1(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
