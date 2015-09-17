(ns p-p-p-pokerface)

(defn suit [card]
  (let [[_ snd] card]
    (str snd)))

(def replacements {\T 10, \J 11, \Q 12, \K 13, \A 14})

(defn rank [card]
  (let [[fst _] card]
    (if (Character/isDigit fst)
      (Integer/valueOf (str fst))
      (replacements fst))))

(defn frequency-checker [hand frequency]
  (> (apply max (vals (frequencies (map rank hand)))) frequency))

(defn pair? [hand]
  (frequency-checker hand 1))

(defn old-pair? [hand]
  (> (apply max (vals (frequencies (map rank hand)))) 1))

(defn three-of-a-kind? [hand]
  (frequency-checker hand 2))

(defn four-of-a-kind? [hand]
  (frequency-checker hand 3))

(defn flush? [hand]
  (> (apply max (vals (frequencies (map suit hand)))) 4))

(defn full-house? [hand]
  nil)

(defn two-pairs? [hand]
  nil)

(defn straight? [hand]
  nil)

(defn straight-flush? [hand]
  nil)

(defn value [hand]
  nil)
