package epitech.Model;

import com.sun.syndication.feed.synd.SyndFeed;

/**
 * Created by lumy on 21/01/2017.
 */
public class Flux {
  private Integer uid;
  private String title;
  private String urlFlux;
  private String urlSite;
  private Integer category;
  private String description;
  private String imageUrl;


  public Flux(String url, SyndFeed f) {
    setTitle(f.getTitle());
    setUrlFlux(url);
    setUrlSite(f.getLink());
    if (null != f.getImage()) {
      setImageUrl(f.getImage().getUrl());
    }
    setDescription(f.getDescription());
  }
  public Flux() {
  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public Integer getCategory() {
    return category;
  }

  public void setCategory(Integer category) {
    this.category = category;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getUrlFlux() {
    return urlFlux;
  }

  public void setUrlFlux(String urlFeed) {
    this.urlFlux = urlFeed;
  }

  public String getUrlSite() {
    return urlSite;
  }

  public void setUrlSite(String urlSite) {
    this.urlSite = urlSite;
  }
}
