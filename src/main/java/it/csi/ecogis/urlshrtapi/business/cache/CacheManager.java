/*******************************************************************************
 * Copyright CSI-Piemonte - 2017-2020
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.ecogis.urlshrtapi.business.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;

public class CacheManager {

	private ConcurrentMap<String, String> _hmCache = null;
	private Map<String, String> _goldCache = null;
	private static CacheManager _self = null;
	
	private CacheManager(long maxCapacity) {
		_hmCache = new ConcurrentLinkedHashMap.Builder<String, String>()
	       	    .maximumWeightedCapacity(maxCapacity).build();
		_goldCache = new HashMap<String, String>();
	}
	
	public static final CacheManager getInstance(long maxCapacity) {
		if (_self == null) {
			_self = new CacheManager(maxCapacity);
		}
		return _self;
	}
	
	public void cache(String key, String value,boolean isGold) {
		if (isGold) {
			_goldCache.put(key, value);
		}
		else {
			_hmCache.put(key, value);
		}
	}
	
	public String retrieveCachedValue(String key,boolean isGold) {
		if (isGold) {
			return _goldCache.get(key);
		}
		else {
			return _hmCache.get(key);
		}
	}
	
	public boolean isCached(String key,boolean isGold) {
		if (isGold) {
			return _goldCache.containsKey(key);
		}
		else {
			return _hmCache.containsKey(key);
		}
	}
	
	public void deleteCacheEntry(String key,boolean isGold) {
		if (isGold) {
			_goldCache.remove(key);
		}
		else {
			_hmCache.remove(key);
		}
		
	}
	
	public void emptyCache(long maxCapacity) {
		_self = new CacheManager(maxCapacity);
	}
}
