import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Created by 1 on 19.07.2017.
 */
public class MP3Player {
    public static volatile boolean stop = false;
    public static String fileName = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\ProjectMP3PPlay\\out\\production\\ProjectMP3PPlay\\музыка.mp3";
    public static File file = new File(fileName);

    public static void main(String[] args) throws InterruptedException {
        //create thread mp3
        final ThreadPlayer threadPlayer = new ThreadPlayer();

        //create frame
        JFrame frame = new JFrame("Проигрователь");
        //to set parameters frame
        frame.setVisible(true);//делает все видимым
        frame.setSize(300, 105);//задаем начальные размеры
        frame.setResizable(false);//запрет на изменеие размеров окна

        //этот метод реализует нажатие на крестик(закрытие окна),
        //теперь он не только закрывает окно, но и останавливает программу
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create panel
        JPanel panel = new JPanel();

        //create meneger компановки(размещения) для panel
        FlowLayout fl = new FlowLayout();
        panel.setLayout(fl);

        //create components a panel
        JLabel label1 = new JLabel("  Вас приветствует Мастер Den! ");
        JLabel label2 = new JLabel("  Управляйте музыкой нажимая кнопку! ");
        JButton start = new JButton("Start/Stop");

        //add components
        panel.add(label1);
        panel.add(label2);
        panel.add(start);

        //binding frame and panel
        frame.setContentPane(panel);




        //на этом с отображением графики закончено

        //слушатель
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                stop = !stop;
            }
        };
        start.addActionListener(actionListener);


    }

    public static class ThreadPlayer extends Thread {

        @Override
        public void run() {
            try {
                FileInputStream f = new FileInputStream(file);
                Player player = new Player(f);
                player.play();
                while (Thread.currentThread().isInterrupted()) {
                    Thread.sleep(1000);
                    System.out.print("*");
                }
            } catch (JavaLayerException exc) {
                System.out.println("Ошибка проигрывателя.");
            } catch (FileNotFoundException exc) {
                exc.printStackTrace();
                System.out.println("Файл не найден.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }
}
