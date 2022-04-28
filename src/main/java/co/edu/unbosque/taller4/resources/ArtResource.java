package co.edu.unbosque.taller4.resources;

import co.edu.unbosque.taller4.services.ArtPieceService;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/arts")
public class ArtResource {

    @Context
    ServletContext context;

    private final String UPLOAD_DIRECTORY = File.separator + "arts";

    @POST
    @Path("/{username}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response uploadFile(MultipartFormDataInput input,
                               @FormParam("username") String username,
                               @FormParam("title")String title,
                               @FormParam("price")String price) {
        System.out.println(title);
        // Getting the file from form input
        Map<String, List<InputPart>> formParts = input.getFormDataMap();
        List<InputPart> inputParts = formParts.get("file");

        for (InputPart inputPart : inputParts) {
            try {
                // Retrieving headers and reading the Content-Disposition header to file name
                MultivaluedMap<String, String> headers = (MultivaluedMap<String, String>) inputPart.getHeaders();
                String fileName = parseFileName();

                String csvPath = context.getRealPath("") + File.separator + "WEB-INF/classes/"+"arts.csv";

                new ArtPieceService().createArt(username,title,price,fileName,csvPath);

                // Handling the body of the part with an InputStream
                InputStream istream = inputPart.getBody(InputStream.class,null);

                saveFile(istream, fileName, context);
            } catch (IOException e) {
                return Response.serverError().build();
            }
        }

        return Response.ok()
                .entity("Image successfully uploaded")
                .build();
    }

    // Parse Content-Disposition header to get the file name
    private String parseFileName() {
        String theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789"+"abcdefghijklmnopqrstuvwxyz";
        StringBuilder builder;

        builder = new StringBuilder(16);

        for (int m = 0; m < 16; m++) {
            int myindex = (int)(theAlphaNumericS.length() * Math.random());

            builder.append(theAlphaNumericS.charAt(myindex));
        }
        String fileName = builder.toString();



        return fileName;
    }

    // Save uploaded file to a defined location on the server
    private void saveFile(InputStream uploadedInputStream, String fileName, ServletContext context) {
        int read = 0;
        byte[] bytes = new byte[1024];

        try {
            // Complementing servlet path with the relative path on the server
            String uploadPath = context.getRealPath("") + UPLOAD_DIRECTORY;

            // Creating the upload folder, if not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            // Persisting the file by output stream
            OutputStream outpuStream = new FileOutputStream(uploadPath + fileName);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }

            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/collections")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createCollection(@FormParam("coleccion")String coleccion){
        String UPLOAD_DIRECTORY_COLLECTIONS = context.getRealPath("") + File.separator+"arts"+File.separator+coleccion;

        File collection = new File(UPLOAD_DIRECTORY_COLLECTIONS);
        if (!collection.exists())collection.mkdir();
        else{
            return Response.ok().entity(coleccion).build();
        }
        return Response.serverError().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArtsImage(){

        String path = context.getRealPath("") +UPLOAD_DIRECTORY;

        File uploadDir = new File(path);

        List<String> files = new ArrayList<String>();
        for (File file : uploadDir.listFiles()) {
            files.add(UPLOAD_DIRECTORY + File.separator + file.getName());
        }

        return Response.status(200).entity(files).build();
    }

}