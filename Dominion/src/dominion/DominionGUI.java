package dominion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;

import java.util.List;

import javax.swing.*;

import dominion.game.state.GameState;
import dominion.game.state.Pile;
import dominion.card.type.Category;

public class DominionGUI {
   private class CardGroup extends JPanel {
      public static final int CARD_WIDTH = 100;
      public static final String IMG_PATH = "img/";
      
      public CardGroup(String title, Category category) {
         setBorder(title);
         renderCategory(category);
      }
      
      public CardGroup(String title, String location) {
         setBorder(title);
         renderLocation(location);
      }
      
      public CardGroup(String title) {
         setBorder(title);
      }
      
      private void setBorder(String title) {
         setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(title),
            BorderFactory.createEmptyBorder(5,5,5,5))
         );
      }
      
      private void renderCategory(Category category) {
         // Obtain the pile
         // TODO: getSupplies(Category) does not yet exist. Implement.
         // List<Pile> cards = GameState.getInstance().getSupplies(category);
         
         // for (Card card : cards) {
         //    this.add(card);
         // }
         
         // TEMPORARY RENDER
         ClassLoader cldr = this.getClass().getClassLoader();
         
         if (category == Category.KINGDOM) {
        	this.setLayout(new GridLayout(2,0,1,10));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "adventurer.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "bureaucrat.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "cellar.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "chancellor.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "chapel.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "councilroom.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "feast.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "festival.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "gardens.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "laboratory.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "library.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "market.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "militia.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "mine.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "moat.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "moneylender.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "remodel.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "smithy.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "spy.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "thief.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "throneroom.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "village.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "witch.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "woodcutter.jpg")));
            // this.add(cardLabel(cldr.getResource(IMG_PATH + "workshop.jpg")));
         }
         else if (category == Category.TREASURE) {
            this.add(cardLabel(cldr.getResource(IMG_PATH + "copper.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "silver.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "gold.jpg")));
         }
         else if (category == Category.VICTORY) {
            this.add(cardLabel(cldr.getResource(IMG_PATH + "estate.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "duchy.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "province.jpg")));
         }
         else if (category == Category.CURSE) {
            this.add(cardLabel(cldr.getResource(IMG_PATH + "curse.jpg")));
         }
      }
      
      private void renderLocation(String location) {
         // TEMPORARY RENDER
         ClassLoader cldr = this.getClass().getClassLoader();
         
         if (location.equals("Hand")) {
            this.add(cardLabel(cldr.getResource(IMG_PATH + "copper.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "copper.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "copper.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "copper.jpg")));
            this.add(cardLabel(cldr.getResource(IMG_PATH + "estate.jpg")));
         }
         else if(location.equals("Deck")) {
            this.add(cardLabel(cldr.getResource(IMG_PATH + "back.jpg")));
         }
         else if(location.equals("Discard")) {
            this.add(cardLabel(cldr.getResource(IMG_PATH + "blank.jpg")));
         }
         else if(location.equals("Trash")) {
            this.add(cardLabel(cldr.getResource(IMG_PATH + "trash.jpg")));
         }
      }

      private JLabel cardLabel(java.net.URL url) {
         JLabel cardLabel = null;
         
         try {
            Image original = (new ImageIcon(url)).getImage();
            double proportion = (double) original.getHeight(this) / original.getWidth(this);
            
            Image scaled = original.getScaledInstance(
               CARD_WIDTH, (int)(CARD_WIDTH * proportion), java.awt.Image.SCALE_SMOOTH
            );
            
            cardLabel = new JLabel(new ImageIcon(scaled));
         } catch (Exception e) {
            e.printStackTrace();
         }
         
         return cardLabel;
      }
   }
   
	public static void main(String[] args) {
	   new DominionGUI();
	}
	
	public DominionGUI() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame f = new JFrame("Dominion");
				
				f.setLayout(new BorderLayout());
				
				JPanel supply = new JPanel();
				supply.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.HORIZONTAL;
				
				c.gridwidth = 4;
				c.gridx = 0; c.gridy = 0;
	        	supply.add(new CardGroup("Kingdom", Category.KINGDOM), c);
	        	
	        	c.gridwidth = 1;
	        	
	        	c.gridx = 0; c.gridy = 1;
				supply.add(new CardGroup("Treasure", Category.TREASURE), c);
				
				c.gridx = 1; c.gridy = 1;
				supply.add(new CardGroup("Victory", Category.VICTORY), c);
				
				c.gridx = 2; c.gridy = 1;
				supply.add(new CardGroup("Curse", Category.CURSE), c);
				
				c.gridx = 3; c.gridy = 1;
				supply.add(new CardGroup("Trash", "Trash"), c);
				
				JPanel lowerThird = new JPanel(new BorderLayout());
				
				JPanel player = new JPanel(new FlowLayout());
				player.add(new CardGroup("Hand", "Hand"));
				player.add(new CardGroup("Deck", "Deck"));
				player.add(new CardGroup("Discard", "Discard"));
				
				JPanel status = new JPanel(new FlowLayout());
				status.add(new JLabel("Status display"));
				
				lowerThird.add(player, BorderLayout.NORTH);
				lowerThird.add(status, BorderLayout.SOUTH);
				
				f.add(supply, BorderLayout.CENTER);
				f.add(lowerThird, BorderLayout.SOUTH);
				
				f.setSize(1024, 768);
				
				f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				
				f.setVisible(true);
			}
		});
	}
}
