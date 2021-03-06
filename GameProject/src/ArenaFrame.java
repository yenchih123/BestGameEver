import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;
public class ArenaFrame extends JFrame
//implements Runnable
{
	//new comment 3
 // new comment
    boolean onesTurn = false;
// new comment 2
    boolean gameEnded = true;
    JButton startButton;
    JButton attackButton;
    Arena arena;
    StartButtonListener startListener;
    AttackButtonListener attackListener;
    JPanel inputPanel;
    JPanel menuPanel;
    JPanel arenaPanel;
    JTextField attackField;
    JTextArea battleArea;
    JLabel enterStartLabel, restartLabel, attackOneLabel, attackTwoLabel;
    JPanel oneAttackPanel, oneItemPanel, twoAttackPanel, twoItemPanel;
    JButton attack11, attack12, attack13, attack14, item11, item12, item13, attack21, attack22, attack23, attack24, item21, item22, item23;
    JButton oneAttackButtons[] = new JButton[4];
    JButton oneItemButtons[] = new JButton[3];
    JButton twoAttackButtons[] = new JButton[4];
    JButton twoItemButtons[] = new JButton[3];
    String input;
    StringBuffer history;
    Thread t1;
    //JPanel northPanel;
    public ArenaFrame()
    {
        arena = new Arena();
        startListener = new StartButtonListener();
        attackListener = new AttackButtonListener();
        setLayout(new BorderLayout());
        startButton = new JButton("Start Battle?");
        attackButton = new JButton("Attack!");
        attack11 = new JButton("None");
        attack12 = new JButton("None");
        attack13 = new JButton("None");
        attack14 = new JButton("None");
        item11 = new JButton("None");
        item12 = new JButton("None");
        item13 = new JButton("None");
        attack21 = new JButton("None");
        attack22 = new JButton("None");
        attack23 = new JButton("None");
        attack24 = new JButton("None");
        item21 = new JButton("None");
        item22 = new JButton("None");
        item23 = new JButton("None");
        inputPanel = new JPanel();
        menuPanel = new JPanel();
        arenaPanel = new JPanel(new BorderLayout());
        oneAttackPanel = new JPanel();
        oneItemPanel = new JPanel();
        twoAttackPanel = new JPanel();
        twoItemPanel = new JPanel();
        attackField = new JTextField(8);
        attackField.setEditable(true);
        battleArea = new JTextArea(10, 50);
        battleArea.setEditable(false);
        battleArea.setMaximumSize(new Dimension(20,20));
        battleArea.setLineWrap(true);
        history = new StringBuffer();
        JScrollPane scroll = new JScrollPane(battleArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JTabbedPane tabbedPaneOne = new JTabbedPane();
        JTabbedPane tabbedPaneTwo = new JTabbedPane();
        tabbedPaneOne.addTab("Attacks", oneAttackPanel);
        tabbedPaneOne.addTab("Items", oneItemPanel);
        tabbedPaneTwo.addTab("Attacks", twoAttackPanel);
        tabbedPaneTwo.addTab("Items", twoItemPanel);
        //t1 = new Thread(this);
        if(arena.one.getSpeed() > arena.two.getSpeed())
        {
            onesTurn = true;
        }   
        enterStartLabel = new JLabel("Press 'Start Battle?' to fight!");
        attackOneLabel = new JLabel("What will " + arena.one.getName() + " do?");
        attackTwoLabel = new JLabel("What will " + arena.two.getName() + " do?");
        restartLabel = new JLabel("Press 'Start Battle' to battle again!");
        oneAttackPanel.add(attack11);
        oneAttackPanel.add(attack12);
        oneAttackPanel.add(attack13);
        oneAttackPanel.add(attack14);
        oneItemPanel.add(item11);
        oneItemPanel.add(item12);
        oneItemPanel.add(item13);
        twoAttackPanel.add(attack21);
        twoAttackPanel.add(attack22);
        twoAttackPanel.add(attack23);
        twoItemPanel.add(item21);
        twoItemPanel.add(item22);
        twoItemPanel.add(item23);
        twoAttackPanel.add(attack24);
        inputPanel.add(enterStartLabel);
        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(inputPanel, BorderLayout.NORTH);
        menuPanel.add(scroll, BorderLayout.SOUTH);
        arena.add(startButton);
        startButton.addActionListener(startListener);
        attackButton.addActionListener(attackListener);
        attackField.addActionListener(attackListener);
        //add(northLabel, BorderLayout.PAGE_START);
        arenaPanel.add(tabbedPaneOne, BorderLayout.WEST);
        arenaPanel.add(arena, BorderLayout.CENTER);
        arenaPanel.add(tabbedPaneTwo, BorderLayout.EAST);
        add(arenaPanel, BorderLayout.CENTER);
        add(menuPanel, BorderLayout.SOUTH);
    }
    public void UIupdate()
    {
        inputPanel.remove(enterStartLabel);
        inputPanel.remove(attackOneLabel);
        inputPanel.remove(attackTwoLabel);

        inputPanel.remove(restartLabel);

        inputPanel.remove(attackField);

        inputPanel.remove(attackButton);

        if(onesTurn && !gameEnded)

        {

            inputPanel.add(attackOneLabel);

        }

        else if (!onesTurn && !gameEnded)

        {

            inputPanel.add(attackTwoLabel);

        }

        else if(gameEnded)

        {

            inputPanel.add(restartLabel);

        }

        inputPanel.add(attackField);

        inputPanel.add(attackButton);

        inputPanel.revalidate();

    }

    public void attackOther(Character attacker, Character victim)

    {

        //attackField.requestFocus();

        //Scanner scanner = new Scanner(System.in);

        //String input = scanner.nextLine().toLowerCase();

        input = attackField.getText();

        boolean didAttack = false;

        if(input != null)

        {

            System.out.println(attackField.getText() + "!");

            for(int i = 0; i < 4; i++)

            {

                if(attacker.attackList[i] != null)
                {
                    if(attacker.attackList[i].getName().compareToIgnoreCase(attackField.getText()) == 0)
                    {
                        System.out.println("doing damage");
                        didAttack = true;
                        if(!didAttack){
                        	history.append(attacker.getName() + " doesn't know that move! AAAAAGH, try again.");
                            //attackOther(attacker, victim);
                        }
                        else
                        {
                        	history.append(attacker.getName() + " used " + input + "! \n");
                        }
                        battleArea.setText(history.toString());
                        attackField.setText("");
                        if(attacker.attackList[i].doDamage(attacker, victim) == true);
                        {
                            history.append("EHMERGAAAAD CRITICAL HIT \n");
                        }
                    }
                }

                
            }

        }

        if(!didAttack){

        	history.append(attacker.getName() + " doesn't know that move! AAAAAGH, try again.");

            //attackOther(attacker, victim);

        }
        else
        {
        	history.append(attacker.getName() + " used " + input + "! \n");
        	
        }

       


       

        battleArea.setText(history.toString());

        attackField.setText("");

    }

    public void battle()

    {

        if(onesTurn)

        {

            attackOther(arena.one, arena.two);

        }   

        else

        {

            attackOther(arena.two, arena.one);

        }

        if(arena.one.getHealth() <= 0){

            gameEnded = true;

            history.append("Game Over. " + arena.two.getName() + " wins. \n");

            arena.two.setExperience(arena.two.getExperience() + 5);

            if(arena.two.getExperience() >= arena.two.getExperienceBar())

            {

                history.append(arena.two.levelUp() + "\n");

            }

        }

        else if(arena.two.getHealth() <= 0)

        {

            gameEnded = true;

            history.append("Game Over. " + arena.one.getName() + " wins. \n");

            arena.one.setExperience(arena.one.getExperience() + 5);

            if(arena.one.getExperience() >= arena.one.getExperienceBar())

            {

                history.append(arena.one.levelUp() + "\n");

            }

        }

        if(onesTurn)

        {

            onesTurn = false;

        }

        else

        {

            onesTurn = true;

        }

        battleArea.setText(history.toString());

        UIupdate();

        arena.repaint();

        /*

            try {

                Thread.sleep(3);

            } catch (InterruptedException e) {

                // TODO Auto-generated catch block

                e.printStackTrace();

            }

         */

        if(gameEnded)

        {

            /*

                Scanner scanner = new Scanner(System.in);

                System.out.println("Type 'yes' to battle again.");

                String input = scanner.nextLine().toLowerCase();

             */

            UIupdate();

            if(input.toString().compareTo("yes") == 0)

            {

                arena.one.fullHeal();

                arena.two.fullHeal();

                //needs restart thing

            }

        }

        System.out.println("Thread ended");

    }

private class StartButtonListener implements ActionListener

{

    public void actionPerformed(ActionEvent arg0) {

        //arena.battle(arena.one, arena.two);

        if(

                //!t1.isAlive() &&

                gameEnded)

        {

            gameEnded = false;

        }

        UIupdate();

    }   

}

private class AttackButtonListener implements ActionListener

{

    public void actionPerformed(ActionEvent arg0){

        if(!gameEnded)

        {

            battle();

            //    t1 = new Thread(new ArenaFrame());

            //    t1.start();

        }

        repaint();

    }

}

public static void main(String[] args)

{

    ArenaFrame frame = new ArenaFrame();

    frame.setSize(1100,700);

    frame.setMinimumSize(new Dimension(1100,700));

    frame.setMaximumSize(new Dimension(1100,700));

    frame.setTitle("RPG Demo");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setVisible(true);

}

}


