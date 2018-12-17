package nl.hypothermic.javacogs.entities;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * A user (profile) on Discogs.
 */
public class UserProfile extends Entity {
	
	@JSONField(name="id")
	public int id;
	
	@JSONField(name="username")
	public String userName;
	
	@JSONField(name="name")
	public String realName;
	
	/*
	 * Confusing name, this is actually the biography of the user.
	 */
	@JSONField(name="profile")
	public String biography;
	
	@JSONField(name="rank")
	public int rank;
	
	@JSONField(name="registered")
	public String registerDate;
	
	@JSONField(name="location")
	public String location;
	
	@JSONField(name="curr_abbr")
	public String currencyAbbrevation;
	
	@JSONField(name="home_page")
	public String homePage;
	
	@JSONField(name="releases_contributed")
	public int releasesContributed;
	
	@JSONField(name="releases_rated")
	public int releasesRated;
	
	@JSONField(name="rating_avg")
	public int averageRating;
	
	@JSONField(name="num_for_sale")
	public int amountForSale;
	
	@JSONField(name="num_pending")
	public int amountPending;
	
	@JSONField(name="num_collection")
	public int collectionSize;
	
	@JSONField(name="num_wantlist")
	public int wantlistSize;
	
	@JSONField(name="num_lists")
	public int listSize;

	@JSONField(name="buyer_rating")
	public double buyerRating;
	
	@JSONField(name="buyer_rating_stars")
	public short buyerRatingStars;
	
	@JSONField(name="buyer_num_ratings")
	public int amountBuyerRatings;
	
	@JSONField(name="seller_rating")
	public double sellerRating;
	
	@JSONField(name="seller_rating_stars")
	public short sellerRatingStars;
	
	@JSONField(name="seller_num_ratings")
	public int amountSellerRatings;
	
	@JSONField(name="wantlist_url")
	public String wantlistUrl;
	
	@JSONField(name="collection_folders_url")
	public String collectionFoldersUrl;
	
	@JSONField(name="collection_fields_url")
	public String collectionFieldsUrl;
	
	@JSONField(name="inventory_url")
	public String inventoryUrl;
	
	@JSONField(name="avatar_url")
	public String avatarUrl;
	
	@JSONField(name="banner_url")
	public String bannerUrl;
	
	@JSONField(name="uri")
	public String uri;
	
	@JSONField(name="resource_url")
	public String resourceUrl;

	public int getId() {
		return this.id;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getRealName() {
		return this.realName;
	}

	public String getBiography() {
		return this.biography;
	}

	public int getRank() {
		return this.rank;
	}

	public String getRegisterDate() {
		return this.registerDate;
	}

	public String getLocation() {
		return this.location;
	}

	public String getCurrencyAbbrevation() {
		return this.currencyAbbrevation;
	}

	public String getHomePage() {
		return this.homePage;
	}

	public int getReleasesContributed() {
		return this.releasesContributed;
	}

	public int getReleasesRated() {
		return this.releasesRated;
	}

	public int getAverageRating() {
		return this.averageRating;
	}

	public int getAmountForSale() {
		return this.amountForSale;
	}

	public int getAmountPending() {
		return this.amountPending;
	}

	public int getCollectionSize() {
		return this.collectionSize;
	}

	public int getWantlistSize() {
		return this.wantlistSize;
	}

	public int getListSize() {
		return this.listSize;
	}

	public double getBuyerRating() {
		return this.buyerRating;
	}

	public short getBuyerRatingStars() {
		return this.buyerRatingStars;
	}

	public int getAmountBuyerRatings() {
		return this.amountBuyerRatings;
	}

	public double getSellerRating() {
		return this.sellerRating;
	}

	public short getSellerRatingStars() {
		return this.sellerRatingStars;
	}

	public int getAmountSellerRatings() {
		return this.amountSellerRatings;
	}

	public String getWantlistUrl() {
		return this.wantlistUrl;
	}

	public String getCollectionFoldersUrl() {
		return this.collectionFoldersUrl;
	}

	public String getCollectionFieldsUrl() {
		return this.collectionFieldsUrl;
	}

	public String getInventoryUrl() {
		return this.inventoryUrl;
	}

	public String getAvatarUrl() {
		return this.avatarUrl;
	}

	public String getBannerUrl() {
		return this.bannerUrl;
	}

	public String getUri() {
		return this.uri;
	}

	public String getResourceUrl() {
		return this.resourceUrl;
	}

	@Override public String toString() {
		return "UserProfile [id=" + this.id + ", userName=" + this.userName + ", realName=" + this.realName + ", biography=" + this.biography + ", rank=" + this.rank + ", registerDate=" + this.registerDate + ", location=" + this.location + ", currencyAbbrevation=" + this.currencyAbbrevation + ", homePage=" + this.homePage + ", releasesContributed=" + this.releasesContributed + ", releasesRated=" + this.releasesRated + ", averageRating=" + this.averageRating + ", amountForSale=" + this.amountForSale + ", amountPending=" + this.amountPending + ", collectionSize=" + this.collectionSize
				+ ", wantlistSize=" + this.wantlistSize + ", listSize=" + this.listSize + ", buyerRating=" + this.buyerRating + ", buyerRatingStars=" + this.buyerRatingStars + ", amountBuyerRatings=" + this.amountBuyerRatings + ", sellerRating=" + this.sellerRating + ", sellerRatingStars=" + this.sellerRatingStars + ", amountSellerRatings=" + this.amountSellerRatings + ", wantlistUrl=" + this.wantlistUrl + ", collectionFoldersUrl=" + this.collectionFoldersUrl + ", collectionFieldsUrl=" + this.collectionFieldsUrl + ", inventoryUrl=" + this.inventoryUrl + ", avatarUrl=" + this.avatarUrl
				+ ", bannerUrl=" + this.bannerUrl + ", uri=" + this.uri + ", resourceUrl=" + this.resourceUrl + "]";
	}
}
