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

(defn three-of-a-kind? [hand]
  (frequency-checker hand 2))

(defn four-of-a-kind? [hand]
  (frequency-checker hand 3))

(defn flush? [hand]
  (> (apply max (vals (frequencies (map suit hand)))) 4))

(defn full-house? [hand]
  (= (sort (vals (frequencies (map rank hand)))) (range 2 4)))

(defn two-pairs? [hand]
  (or (= (take 2 (vals (frequencies (map rank hand)))) [2 2])
      (four-of-a-kind? hand)))

(defn straight? [hand]
  (let [sorted-hand-by-rank (sort (map rank hand))
        sorted-hand-by-rank-ace (sort (replace {14 1} (map rank hand)))
        sorted-hand-by-rank-lowest-card (first sorted-hand-by-rank)]
  (or (= sorted-hand-by-rank (range sorted-hand-by-rank-lowest-card (+ sorted-hand-by-rank-lowest-card 5)))
      (= sorted-hand-by-rank-ace (range 1 6)))))

(defn straight-flush? [hand]
  (and (straight? hand)
       (flush? hand)))

(defn high-card? [hand]
  true) ; All hands have a high card.

(defn value [hand]
  (cond
    (straight-flush? hand) 8
    (four-of-a-kind? hand) 7
    (full-house? hand) 6
    (flush? hand) 5
    (straight? hand) 4
    (three-of-a-kind? hand) 3
    (two-pairs? hand) 2
    (pair? hand) 1
    :else 0))
