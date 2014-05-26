/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.parser;

import android.content.Context;
import android.os.Environment;

import com.dim.swimlap.db.builder.DbUtilitiesBuilder;
import com.dim.swimlap.db.recorder.RecordParsingInDb;
import com.dim.swimlap.models.MeetingModel;

import org.xmlpull.v1.XmlPullParserException;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.SQLException;

public class FFNexDataGetter {

    private File swimLapDir, ffnexDir, ffnexReaded;
    private String[] files;

    public void createDirectory() throws IOException {
        File root = Environment.getExternalStorageDirectory();
        swimLapDir = new File(root, "swimlap");
        swimLapDir.mkdir();
        ffnexDir = new File(swimLapDir, "ffnex");
        ffnexDir.mkdir();
        ffnexReaded = new File(swimLapDir, "ffnexReaded");
        ffnexReaded.mkdir();
    }

    public String[] getFiles() {
        files = ffnexDir.list();
        return files;
    }

    public File getFFNexFile(String name) {
        return new File(ffnexDir, name);
    }

    public String transformFileToString(File fileXML) throws IOException {
        FileInputStream fis = new FileInputStream(fileXML);
        byte[] buffer = new byte[(int) fileXML.length()];
        new DataInputStream(fis).readFully(buffer);
        fis.close();
        return  new String(buffer, "UTF-8");
    }

    public MeetingModel getResultOfParsing(String stringXML,Context context) throws IOException, XmlPullParserException {
        FFNexParser parser = new FFNexParser(context);
        parser.parseIt(stringXML);
        return parser.getBackMeetingModel();
    }

    public boolean recordParsedMeetingInDb(MeetingModel meetingModel, Context context) {
        RecordParsingInDb recorder = new RecordParsingInDb(context);
        return recorder.recordMeetingFromFFNex(meetingModel);
    }

    public boolean recordParsingHasBeenDone(int idMeetting, Context context) {
        DbUtilitiesBuilder db = new DbUtilitiesBuilder(context);
        boolean meetingAlreadyExists = false;
        try{
            db.open();
            meetingAlreadyExists = db.getMeetingUtilities().meetingAlready_InDb(idMeetting);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return meetingAlreadyExists;
    }

    public void moveFFNexParsed(String fileNameParsed) {
        File fileSrc = new File(ffnexDir, fileNameParsed);
        File fileDest = new File(ffnexReaded, fileNameParsed);
        FileChannel readChannel = null;
        FileChannel writeChannel = null;

        try {
            readChannel = new FileInputStream(fileSrc).getChannel();
            writeChannel = new FileOutputStream(fileDest).getChannel();

            long bytesMoved = readChannel.transferTo(0, readChannel.size(), writeChannel);
            if (bytesMoved == readChannel.size()) {
                fileSrc.delete();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (readChannel != null)
                    readChannel.close();
                if (writeChannel != null)
                    writeChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
