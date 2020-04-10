import java.io.*;
import java.util.Scanner;

public class Main {

    private static int upperLatina = 0;
    private static int lowerLatina = 0;
    private static int upperRus = 0;
    private static int lowerRus = 0;
    private static int specialSymbol = 0;
    private static char shift = 23;
    private static char bigRus[] = {'А' ,'Б' ,'В' ,'Г' ,'Д' ,'Е' , 'Ё', 'Ж' ,'З' ,'И' ,'Й' ,'К' ,'Л' ,'М' ,'Н' ,'О' ,'П' ,
                                    'Р' ,'С' ,'Т' ,'У' ,'Ф' ,'Х' ,'Ц' ,'Ч' ,'Ш' ,'Щ' ,'Ъ' ,'Ы' ,'Ь' ,'Э' ,'Ю' ,'Я'};
    private static char lowRus[] = {'а' ,'б' ,'в' ,'г' ,'д' ,'е' ,'ё', 'ж' ,'з' ,'и' ,'й' ,'к' ,'л' ,'м' ,'н' ,'о' ,'п' ,
                                    'р' ,'с' ,'т' ,'у' ,'ф' ,'х' ,'ц' ,'ч' ,'ш' ,'щ' ,'ъ' ,'ы' ,'ь' ,'э' ,'ю' ,'я'};

    public static void main(String[] args) throws IOException {
        String str = null;
        try {
            FileReader fr = new FileReader("input.txt");
            Scanner scan = new Scanner(fr);
            str = scan.nextLine();
            try{
                fr.close();
            }catch (IOException e){
                System.out.println("Файл не закрыт!");
            }
        }catch(FileNotFoundException e){
            System.out.println("Файл не создан!");
        }
        char[] unencryptedLetter  = str.toCharArray();
        for (int i = 0; i < unencryptedLetter.length; i++) {
            System.out.print(unencryptedLetter[i]);
        }
        System.out.println();
        for(int i = 0; i < unencryptedLetter.length; i++){
            switch (unencryptedLetter[i]){
                case 'A': case 'B': case 'C': case 'D': case 'E': case 'F': case 'G': case 'H': case 'I': case 'J':
                case 'K': case 'L': case 'M': case 'N': case 'O': case 'P': case 'Q': case 'R': case 'S': case 'T':
                case 'U': case 'V': case 'W': case 'X': case 'Y': case 'Z':
                                                                            upperLatina++;
                                                                            break;
                case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h': case 'i': case 'j':
                case 'k': case 'l': case 'm': case 'n': case 'o': case 'p': case 'q': case 'r': case 's': case 't':
                case 'u': case 'v': case 'w': case 'x': case 'y': case 'z':
                                                                            lowerLatina++;
                                                                            break;
                case 1040: case 1041: case 1042: case 1043: case 1044: case 1045: case 1046: case 1047: case 1048:
                case 1049: case 1050: case 1051: case 1052: case 1053: case 1054: case 1055: case 1056: case 1057:
                case 1058: case 1059: case 1060: case 1061: case 1062: case 1063: case 1064: case 1065: case 1066:
                case 1067: case 1068: case 1069: case 1070: case 1071: case 'Ё':
                                                                                upperRus++;
                                                                                break;
                case 1072: case 1073: case 1074: case 1075: case 1076: case 1077: case 1078: case 1079: case 1080:
                case 1081: case 1082: case 1083: case 1084: case 1085: case 1086: case 1087: case 1088: case 1089:
                case 1090: case 1091: case 1092: case 1093: case 1094: case 1095: case 1096: case 1097: case 1098:
                case 1099: case 1100: case 1101: case 1102: case 1103: case 'ё':
                                                                                lowerRus++;
                                                                                break;
                default:
                    specialSymbol++;
            }
        }
        System.out.println("Заглавных английских: " + upperLatina);
        System.out.println("Прописных английских: " + lowerLatina);
        System.out.println("Заглавных русских: " + upperRus);
        System.out.println("Прописных русских: " + lowerRus);
        System.out.println("Специальных символов: " + specialSymbol);
        char tmp = 0;
        int tret = shift % 27, trot = shift % 33;
        FileWriter wr = new FileWriter("output.txt");
        for(int i = 0; i < unencryptedLetter.length; i++){
            if((int)unencryptedLetter[i] >= 65 && (int)unencryptedLetter[i] <= 90){
                tmp = (char)(unencryptedLetter[i] + tret);
                if(tmp > 90)
                    tmp -= 26;
                wr.append(tmp);
            }else if((int)unencryptedLetter[i] >= 97 && (int)unencryptedLetter[i] <= 122){
                tmp = (char)(unencryptedLetter[i] + tret);
                if(tmp > 122)
                    tmp -= 26;
                wr.append(tmp);
            }else if(((int)unencryptedLetter[i] >= 1040 && (int)unencryptedLetter[i] <= 1071) || unencryptedLetter[i] == 'Ё'){
                for (int j = 0; j < 33; j++){
                    if(bigRus[j] == unencryptedLetter[i]){
                        wr.append(bigRus[(j+trot)%33]);
                    }
                }
            }else if(((int)unencryptedLetter[i] >= 1072 && (int)unencryptedLetter[i] <= 1103) || unencryptedLetter[i] == 'ё'){
                for (int j = 0; j < 33; j++){
                    if(lowRus[j] == unencryptedLetter[i]){
                        wr.append(lowRus[(j+trot)%33]);
                    }
                }
            }else
                wr.append(unencryptedLetter[i]);
        }
        wr.close();
        //Дешивровка полученного сообщения
        int k = 0;
        String message = null;
        try {
            FileReader read = new FileReader("output.txt");
            Scanner scan = new Scanner(read);
            message = scan.nextLine();
            try{
                read.close();
            }catch (IOException e){
                System.out.println("Файл не закрыт!");
            }
        }catch(FileNotFoundException e){
            System.out.println("Файл не создан!");
        }
        char[] encryptedLetter  = message.toCharArray();
        FileWriter writer = new FileWriter("data.txt");
        for(int i = 0; i < encryptedLetter.length; i++){
            if((int)encryptedLetter[i] >= 65 && (int)encryptedLetter[i] <= 90){
                tmp = (char)(encryptedLetter[i] - tret);
                if(tmp < 65)
                    tmp += 26;
                writer.append(tmp);
            }else if((int)encryptedLetter[i] >= 97 && (int)encryptedLetter[i] <= 122){
                tmp = (char)(encryptedLetter[i] - tret);
                if(tmp < 97)
                    tmp += 26;
                writer.append(tmp);
            }else if(((int)encryptedLetter[i] >= 1040 && (int)encryptedLetter[i] <= 1071) || (encryptedLetter[i] == 'Ё')){
                for (int j = 0; j < 33; j++){
                    if(bigRus[j] == encryptedLetter[i]){
                        if(j-trot < 0){
                            k = 33 + j - trot;
                            writer.append(bigRus[k]);
                        }else
                            writer.append(bigRus[j-trot]);
                    }
                }
            }else if(((int)encryptedLetter[i] >= 1072 && (int)encryptedLetter[i] <= 1103) || (encryptedLetter[i] == 'ё')){
                for (int j = 0; j < 33; j++){
                    if(lowRus[j] == encryptedLetter[i]){
                        if(j-trot < 0){
                            k = 33 + j - trot;
                            writer.append(lowRus[k]);
                        }else
                            writer.append(lowRus[j-trot]);
                    }
                }
            }else
                writer.append(encryptedLetter[i]);
        }
        writer.close();
    }
}