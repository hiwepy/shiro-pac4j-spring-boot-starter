/*
 * Copyright (c) 2017, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.shiro.spring.boot;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.spring.boot.pac4j.property.ShiroPac4jOAuthCasClientProperties;
import org.apache.shiro.spring.boot.pac4j.property.ShiroPac4jOAuthClientProperties;
import org.apache.shiro.spring.boot.pac4j.property.ShiroPac4jOAuthFacebookClientProperties;
import org.apache.shiro.spring.boot.pac4j.property.ShiroPac4jOAuthGenericProperties;
import org.apache.shiro.spring.boot.pac4j.property.ShiroPac4jOAuthOkClientProperties;
import org.apache.shiro.spring.boot.pac4j.property.ShiroPac4jOAuthStravaClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(ShiroPac4jOAuthProperties.PREFIX)
public class ShiroPac4jOAuthProperties {

	public static final String PREFIX = "shiro.pac4j.oauth";
	
	/** Whether Enable Pac4j OAuth. */
	private boolean enabled = false;

	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties baidu = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties bitbucket = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthCasClientProperties cas = new ShiroPac4jOAuthCasClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties dropbox = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthFacebookClientProperties facebook = new ShiroPac4jOAuthFacebookClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties foursquare = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private List<ShiroPac4jOAuthGenericProperties> generics = new ArrayList<ShiroPac4jOAuthGenericProperties>();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties github = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties google2 = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties linkedin2 = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthOkClientProperties ok = new ShiroPac4jOAuthOkClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties orcid = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties oschina = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties paypal = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties qq = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties renren = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthStravaClientProperties strava = new ShiroPac4jOAuthStravaClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties sohu = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties twitter = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties vk = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties weibo = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties weixin = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties windowslive = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties wordpress = new ShiroPac4jOAuthClientProperties();
	@NestedConfigurationProperty
	private ShiroPac4jOAuthClientProperties yahoo = new ShiroPac4jOAuthClientProperties();
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public ShiroPac4jOAuthClientProperties getBaidu() {
		return baidu;
	}

	public void setBaidu(ShiroPac4jOAuthClientProperties baidu) {
		this.baidu = baidu;
	}

	public ShiroPac4jOAuthClientProperties getBitbucket() {
		return bitbucket;
	}

	public void setBitbucket(ShiroPac4jOAuthClientProperties bitbucket) {
		this.bitbucket = bitbucket;
	}

	public ShiroPac4jOAuthCasClientProperties getCas() {
		return cas;
	}

	public void setCas(ShiroPac4jOAuthCasClientProperties cas) {
		this.cas = cas;
	}

	public ShiroPac4jOAuthClientProperties getDropbox() {
		return dropbox;
	}

	public void setDropbox(ShiroPac4jOAuthClientProperties dropbox) {
		this.dropbox = dropbox;
	}

	public ShiroPac4jOAuthFacebookClientProperties getFacebook() {
		return facebook;
	}

	public void setFacebook(ShiroPac4jOAuthFacebookClientProperties facebook) {
		this.facebook = facebook;
	}

	public ShiroPac4jOAuthClientProperties getFoursquare() {
		return foursquare;
	}

	public void setFoursquare(ShiroPac4jOAuthClientProperties foursquare) {
		this.foursquare = foursquare;
	}

	public List<ShiroPac4jOAuthGenericProperties> getGenerics() {
		return generics;
	}

	public void setGenerics(List<ShiroPac4jOAuthGenericProperties> generics) {
		this.generics = generics;
	}

	public ShiroPac4jOAuthClientProperties getGithub() {
		return github;
	}

	public void setGithub(ShiroPac4jOAuthClientProperties github) {
		this.github = github;
	}

	public ShiroPac4jOAuthClientProperties getGoogle2() {
		return google2;
	}

	public void setGoogle2(ShiroPac4jOAuthClientProperties google2) {
		this.google2 = google2;
	}

	public ShiroPac4jOAuthClientProperties getLinkedin2() {
		return linkedin2;
	}

	public void setLinkedin2(ShiroPac4jOAuthClientProperties linkedin2) {
		this.linkedin2 = linkedin2;
	}

	public ShiroPac4jOAuthOkClientProperties getOk() {
		return ok;
	}

	public void setOk(ShiroPac4jOAuthOkClientProperties ok) {
		this.ok = ok;
	}

	public ShiroPac4jOAuthClientProperties getOrcid() {
		return orcid;
	}

	public void setOrcid(ShiroPac4jOAuthClientProperties orcid) {
		this.orcid = orcid;
	}
	
	public ShiroPac4jOAuthClientProperties getOschina() {
		return oschina;
	}

	public void setOschina(ShiroPac4jOAuthClientProperties oschina) {
		this.oschina = oschina;
	}

	public ShiroPac4jOAuthClientProperties getPaypal() {
		return paypal;
	}

	public void setPaypal(ShiroPac4jOAuthClientProperties paypal) {
		this.paypal = paypal;
	}

	public ShiroPac4jOAuthClientProperties getQq() {
		return qq;
	}

	public void setQq(ShiroPac4jOAuthClientProperties qq) {
		this.qq = qq;
	}

	public ShiroPac4jOAuthClientProperties getRenren() {
		return renren;
	}

	public void setRenren(ShiroPac4jOAuthClientProperties renren) {
		this.renren = renren;
	}

	public ShiroPac4jOAuthStravaClientProperties getStrava() {
		return strava;
	}

	public void setStrava(ShiroPac4jOAuthStravaClientProperties strava) {
		this.strava = strava;
	}

	public ShiroPac4jOAuthClientProperties getSohu() {
		return sohu;
	}

	public void setSohu(ShiroPac4jOAuthClientProperties sohu) {
		this.sohu = sohu;
	}

	public ShiroPac4jOAuthClientProperties getTwitter() {
		return twitter;
	}

	public void setTwitter(ShiroPac4jOAuthClientProperties twitter) {
		this.twitter = twitter;
	}

	public ShiroPac4jOAuthClientProperties getVk() {
		return vk;
	}

	public void setVk(ShiroPac4jOAuthClientProperties vk) {
		this.vk = vk;
	}

	public ShiroPac4jOAuthClientProperties getWeibo() {
		return weibo;
	}

	public void setWeibo(ShiroPac4jOAuthClientProperties weibo) {
		this.weibo = weibo;
	}

	public ShiroPac4jOAuthClientProperties getWeixin() {
		return weixin;
	}

	public void setWeixin(ShiroPac4jOAuthClientProperties weixin) {
		this.weixin = weixin;
	}

	public ShiroPac4jOAuthClientProperties getWindowslive() {
		return windowslive;
	}

	public void setWindowslive(ShiroPac4jOAuthClientProperties windowslive) {
		this.windowslive = windowslive;
	}

	public ShiroPac4jOAuthClientProperties getWordpress() {
		return wordpress;
	}

	public void setWordpress(ShiroPac4jOAuthClientProperties wordpress) {
		this.wordpress = wordpress;
	}

	public ShiroPac4jOAuthClientProperties getYahoo() {
		return yahoo;
	}

	public void setYahoo(ShiroPac4jOAuthClientProperties yahoo) {
		this.yahoo = yahoo;
	}

}
