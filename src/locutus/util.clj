(ns locutus.util)

(declare map->stringify-all)

(defn ->str [x]
  (cond (map? x) (map->stringify-all x)
        (keyword? x) (name x)
        :else (str x)))

(defn map->stringify-all [m]
  (into {} (for [[k v] m]
             [(->str k) (->str v)])))
