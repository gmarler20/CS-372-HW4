public class ThreadTest {
    private String[] webpages = {
            "https://www.portlandoregon.gov/parks/article/639578",
            "https://www.whitworth.edu/cms/",
            "http://www.espn.com/",
            "https://osubeavers.com/",
            "https://www.facebook.com/",
            "https://www.quantcast.com/top-sites/"



    };

    public ThreadTest() {
        WebpageReader[] cs = new WebpageReader[webpages.length];
        for(int i = 0; i < cs.length; i++) {
            cs[i] = new WebpageReader(webpages[i]);
        }

        Thread[] ts = new Thread[webpages.length];
        for(int i = 0; i < ts.length; i++) {
            ts[i] = new Thread(cs[i]);
            ts[i].start();
        }
        try{
            Thread.sleep(100);
        } catch(InterruptedException e) {
            ;
        }

        for(int i = 0; i < ts.length; i++) {
            try {
                ts[i].join();
            }catch(InterruptedException ex) {
                ;
            }
        }
    }



    public static void main(String[] args) {
        ThreadTest t = new ThreadTest();



        WebpageReader.printEmails();
        System.out.println("Done");
        System.out.println("Done");
    }
}
