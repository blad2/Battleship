class User extends BaseEntity {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public long getVersion() {
        return this.version;
    }
}

class Visit extends BaseEntity {

    protected User user;

    protected WebSite site;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WebSite getSite() {
        return site;
    }

    public void setSite(WebSite site) {
        this.site = site;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public long getVersion() {
        return this.version;
    }
}

class WebSite extends BaseEntity {

    protected String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public long getVersion() {
        return this.version;
    }
}

abstract class BaseEntity {
    protected long id;
    protected long version;

    public abstract void setId(long id);

    public abstract long getId();

    public abstract void setVersion(long version);

    public abstract long getVersion();

}