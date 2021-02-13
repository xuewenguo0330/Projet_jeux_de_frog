package graphicalElements;

import javax.imageio.ImageIO;
import javax.swing.*;

import gameCommons.Direction;
import gameCommons.IFrog;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
	private ArrayList<Element> elementsToDisplay;
	private BufferedImage car;
	private BufferedImage carl;
	private BufferedImage river;
	private BufferedImage frogger;
	private BufferedImage lane;
	private BufferedImage wood;
	private BufferedImage woodl;
	private BufferedImage glissade;
	private BufferedImage bonus;
	private BufferedImage mur;
	private BufferedImage piege;
	private int pixelByCase = 16;
	private int width;
	private int height;
	private IFrog frog;
	private JFrame frame;
	private Timer timer;
	private int hour = 0, minute = 0, second = 0;
	private String time = "0" + hour + ":0" + minute + ":0" + second;

	public FroggerGraphic(int width, int height) throws IOException {
		this.width = width;
		this.height = height;
		elementsToDisplay = new ArrayList<Element>();
		Initiat();
		Color color=new Color(60, 21,76);
		setBackground(color);
		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));

		JFrame frame = new JFrame("Frogger");
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);
		timer = new Timer(1000, arg0 -> {
			second++;
			if (second > 59) {
				second = 0;
				minute++;
			}
			if (minute > 59) {
				minute = 0;
				hour++;
			}
			time = hour + ":" + minute + ":" + second;
			repaint();
		});
		timer.start();
	}
	private void Initiat() throws IOException {
		this.car = ImageIO.read(new File("Block/car.png"));
		this.frogger = ImageIO.read(new File("Block/frog.png"));
		this.carl = ImageIO.read(new File("Block/car1.png"));
		this.lane = ImageIO.read(new File("Block/lane.png"));
		this.river=ImageIO.read(new File("Block/river.png"));
		this.wood = ImageIO.read(new File("Block/wood.png"));
		this.woodl = ImageIO.read(new File("Block/woodl.png"));
		this.glissade=ImageIO.read(new File("Block/wind.png"));
		this.mur = ImageIO.read(new File("Block/mur.png"));
		this.bonus = ImageIO.read(new File("Block/coin.png"));
		this.piege = ImageIO.read(new File("Block/piege.png"));
	}

	private BufferedImage select_image(Element e){
		switch (e.block){
			case car:
				return this.car;
			case carl:
				return this.carl;
			case frog:
				return this.frogger;
			case lane:
				return this.lane;
			case wood:
				return this.wood;
			case woodl:
				return this.woodl;
			case mur:
				return this.mur;
			case river:
				return this.river;
			case bonus:
				return this.bonus;
			case glissade:
				return this.glissade;
			case piege:
				return this.piege;
		}
		System.out.println("WARNING");
		return null;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < elementsToDisplay.size() ; i++) {
			g.drawImage(select_image(elementsToDisplay.get(i)),
					pixelByCase * elementsToDisplay.get(i).absc,
					pixelByCase * (height - 1 - elementsToDisplay.get(i).ord),
					this);
		}
		g.setColor(Color.black);
		int stringWidth = g.getFontMetrics().stringWidth(time);
		int xCoordinate = getWidth() - stringWidth;
		int yCoordinate = getHeight();
		g.drawString(time, xCoordinate, yCoordinate);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			frog.move(Direction.up);
			break;
		case KeyEvent.VK_DOWN:
			frog.move(Direction.down);
			break;
		case KeyEvent.VK_LEFT:
			frog.move(Direction.left);
			break;
		case KeyEvent.VK_RIGHT:
			frog.move(Direction.right);
		}
	}

	public void clear() {
		this.elementsToDisplay.clear();
	}

	public void add(Element e) {
		this.elementsToDisplay.add(e);
	}

	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	public void endGameScreen(String s) {
		timer.stop();
		String x = "<html><body>" + s + "<br/>"+"You spend : "+ time + "</body></html> ";
		frame.remove(this);
		JLabel label = new JLabel(x);
		label.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		frame.getContentPane().add(label);
		frame.repaint();

	}

}
