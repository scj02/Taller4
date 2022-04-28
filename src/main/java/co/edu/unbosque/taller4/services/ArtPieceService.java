package co.edu.unbosque.taller4.services;

import co.edu.unbosque.taller4.dtos.ArtPiece;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public class ArtPieceService {

    public Optional<List<ArtPiece>> getArtPiece() throws IOException{

        List<ArtPiece> artPieces;

        //verifica que el archivo existe
        try(InputStream is = UserService.class.getClassLoader().getResourceAsStream("arts.csv")){
            if(is==null){
                return Optional.empty();
            }

            //crea la estrategia de lectura para el csv
            HeaderColumnNameMappingStrategy<ArtPiece> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(ArtPiece.class);

            //lee el csv y lo guarda en una lista
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))){
                CsvToBean<ArtPiece> csvToBean = new CsvToBeanBuilder<ArtPiece>(br).withType(ArtPiece.class).withMappingStrategy(strategy).withIgnoreLeadingWhiteSpace(true).build();

                artPieces = csvToBean.parse();
            }
        }

        return Optional.of(artPieces);
    }

    public Optional<Boolean> createArt(String username, String title, String price, String filename, String path) throws IOException {
        try{
            String newLine = username+ "," +title+ "," +price + "," + filename+"\n";
            FileOutputStream os = new FileOutputStream(path, true);
            os.write(newLine.getBytes());
            os.close();
            return Optional.of(true);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.of(false);
        }
    }
}
