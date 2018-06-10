package assignment8.main;

import assignment8.data.Message;
import assignment8.data.User;
import assignment8.data.Vote;
import assignment8.data.VoteType;
import com.owlike.genson.Genson;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.time.LocalDateTime;

public class ServerMain {

    private static final String CONTEXT_PATH = "/zickzack";
    private static final String WEB_APP_LOCATION = "src/main/webapp/";
    private static final String WEB_APP_MOUNT = "/WEB-INF/classes";
    private static final String WEB_APP_CLASSES = "target/classes";

    public static void main(String... args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(5000);

        Context context = tomcat.addWebapp(CONTEXT_PATH, new File(WEB_APP_LOCATION).getAbsolutePath());
        String pathToClasses = new File(WEB_APP_CLASSES).getAbsolutePath();
        WebResourceRoot resources = new StandardRoot(context);
        DirResourceSet dirResourceSet = new DirResourceSet(resources, WEB_APP_MOUNT, pathToClasses, "/");

        Genson genson = new Genson();
        Vote vote = new Vote();
        vote.setAuthor(null);
        vote.setCreatedAt(LocalDateTime.of(2018,6,10,17,0));
        vote.setVoteType(new VoteType(true));


        System.out.println(genson.serialize(vote));

        resources.addPreResources(dirResourceSet);
        context.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }
}
