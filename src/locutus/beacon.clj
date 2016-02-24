(ns locutus.beacon)

(def known-beacons
  {"ec00fb52-740a-40f6-aa96-2d4d8120c567" :hsl})

(defn distance [tx-power rssi]
  (Math/pow 10 (* 0.05 (- tx-power rssi))))

(defn accuracy [tx-power rssi]
  (if (<= (Math/abs rssi) 0.0001)
    -1
    (let [ratio (/ rssi tx-power)]
      (if (< ratio 1.0)
        (Math/pow ratio 10)
        (+ (* 0.89976 (Math/pow ratio 7.7095)) 0.111)))))
