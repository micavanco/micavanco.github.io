package files;

public class Book {
    private String title;
    private String author;
    private int pages;
    private String pageUrl;
    private String coverUrl;
    public Book(String title,String author,String pageUrl,String coverUrl)
    {
        this.title=title;
        this.author=author;
        this.pageUrl=pageUrl;
        this.coverUrl=coverUrl;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
