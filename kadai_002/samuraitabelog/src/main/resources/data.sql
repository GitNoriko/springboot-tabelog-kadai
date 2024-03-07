-- storesテーブル
INSERT IGNORE INTO stores (id, name, image_name, description, lowest_price, highest_price, postal_code, address, opening_time, closing_time, regular_holidays, seating_capacity, category_id) VALUES (1, 'みそかつ', 'store01.jpg', 'おすすめ', 3000, 4000, '450-0000', '愛知県名古屋市テスト区', '11:00', '20:00', '年中無休', 130, 1);
INSERT IGNORE INTO stores (id, name, image_name, description, lowest_price, highest_price, postal_code, address, opening_time, closing_time, regular_holidays, seating_capacity, category_id) VALUES (2, '手羽先', 'store02.jpg', 'おすすめです！', 4000, 5000, '450-0000', '愛知県名古屋市テスト区', '10:00', '20:00', '火、金、日', 140, 1);
INSERT IGNORE INTO stores (id, name, image_name, description, lowest_price, highest_price, postal_code, address, opening_time, closing_time, regular_holidays, seating_capacity, category_id) VALUES (3, 'ひつまぶし', 'store03.jpg', 'おすすめです！', 4000, 5000, '450-0000', '愛知県名古屋市テスト区','13:00', '23:00', '年中無休', 60, 2);
INSERT IGNORE INTO stores (id, name, image_name, description, lowest_price, highest_price, postal_code, address, opening_time, closing_time, regular_holidays, seating_capacity, category_id) VALUES (4, '天むす', 'store04.jpg', 'おすすめです！', 2000, 3000, '450-0000', '愛知県名古屋市テスト区', '09:00', '21:00', '月、水', 120, 1);
INSERT IGNORE INTO stores (id, name, image_name, description, lowest_price, highest_price, postal_code, address, opening_time, closing_time, regular_holidays, seating_capacity, category_id) VALUES (5, 'きしめん', 'store05.jpg', 'おすすめです！', 5000, 6000, '450-0000', '愛知県名古屋市テスト区', '09:30', '22:00', '月、金', 80, 3);
INSERT IGNORE INTO stores (id, name, image_name, description, lowest_price, highest_price, postal_code, address, opening_time, closing_time, regular_holidays, seating_capacity, category_id) VALUES (6, '味噌煮込みうどん', 'store06.jpg', 'おすすめです！', 3000, 4000, '450-0000', '愛知県名古屋市テスト区', '10:00', '20:00', '月、水', 50, 3);
INSERT IGNORE INTO stores (id, name, image_name, description, lowest_price, highest_price, postal_code, address, opening_time, closing_time, regular_holidays, seating_capacity, category_id) VALUES (7, 'カレーうどん', 'store07.jpg', 'おすすめです！', 1000, 2000, '450-0000', '愛知県名古屋市テスト区', '10:00', '20:30', '月、水', 50, 3);
INSERT IGNORE INTO stores (id, name, image_name, description, lowest_price, highest_price, postal_code, address, opening_time, closing_time, regular_holidays, seating_capacity, category_id) VALUES (8, '名古屋コーチン', 'store08.jpg', 'おすすめです！', 5000, 6000, '450-0000', '愛知県名古屋市テスト区', '13:00', '23:00', '水', 120, 1);
INSERT IGNORE INTO stores (id, name, image_name, description, lowest_price, highest_price, postal_code, address, opening_time, closing_time, regular_holidays, seating_capacity, category_id) VALUES (9, '味噌おでん', 'store09.jpg', 'おすすめです！', 4000, 5000, '450-0000', '愛知県名古屋市テスト区', '13:00', '23:00', '日', 100, 1);
INSERT IGNORE INTO stores (id, name, image_name, description, lowest_price, highest_price, postal_code, address, opening_time, closing_time, regular_holidays, seating_capacity, category_id) VALUES (10, 'あんかけスパ', 'store10.jpg', 'おすすめです！', 2000, 4000, '450-0000', '愛知県名古屋市テスト区', '11:00', '20:30', '不定休', 70, 4);

-- categoryテーブル
INSERT IGNORE INTO category (id, name) VALUES(1, '和食');
INSERT IGNORE INTO category (id, name) VALUES(2, '定食');
INSERT IGNORE INTO category (id, name) VALUES(3, 'うどん');
INSERT IGNORE INTO category (id, name) VALUES(4, 'スパゲッティ');

-- rolesテーブル
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_MEMBERSHIP');
INSERT IGNORE INTO roles (id, name) VALUES (3, 'ROLE_ADMIN');

-- usersテーブル
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (1, '侍 太郎', 'サムライ タロウ', '123-4567', '東京都千代田区神田練塀町300', '090-1234-5678', 'taro.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (2, '侍 花子', 'サムライ ハナコ', '123-4567', '東京都千代田区神田練塀町300', '090-1234-5678', 'hanako.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 3, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (3, '侍 次郎', 'サムライ ジロウ', '678-9111', '東京都神田神保町X-XX-XX', '090-1234-5678', 'girou.samurai@example.com', 'password', 1, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (4, '侍 三郎', 'サムライ サブロウ', '121-1314', '東京都池袋X-XX-XX', '090-1234-5678', 'saburou.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (5, '侍 四郎', 'サムライ シロウ', '151-6171', '福岡県太宰府市X-XX-XX', '090-1234-5678', 'sirou.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (6, '侍 五郎', 'サムライ ゴロウ', '819-2012', '大阪府桐蔭X-XX-XX', '090-1234-5678', 'gorou.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (7, '侍 六郎', 'サムライ ロクロウ', '223-2425', '長崎県南島原市X-XX-XX', '090-1234-5678', 'rokurou.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (8, '侍 七郎', 'サムライ ナナロウ', '262-7282', '奈良県橿原市御坊町X-XX-XX', '090-1234-5678', 'nanarou.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (9, '侍 八郎', 'サムライ ハチロウ', '303-1323', '長崎県長崎市X-XX-XX', '090-1234-5678', 'hachirou.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (10, '侍 九朗', 'サムライ クロウ', '334-3536', '長野県木曾郡木曾村X-XX-XX', '090-1234-5678', 'kurou.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (11, '侍 十郎', 'サムライ ジュウロウ', '373-3940', '新潟県新潟市中央区X-XX-XX', '090-1234-5678', 'jurou.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (12, '侍 一郎', 'サムライ イチロウ', '414-2434', '山形県山形市X-XX-XX', '090-1234-5678', 'ichirou.samurai@example.com', 'password', 1, false);

-- reservationsテーブル
INSERT IGNORE INTO reservations (id, store_id, user_id, checkin_date, checkin_time, number_of_people ) VALUES (1, 1, 1, '2023-04-01', '12:00', 1);
INSERT IGNORE INTO reservations (id, store_id, user_id, checkin_date, checkin_time, number_of_people ) VALUES (2, 2, 1, '2023-04-01', '12:00', 2);
INSERT IGNORE INTO reservations (id, store_id, user_id, checkin_date, checkin_time, number_of_people ) VALUES (3, 3, 1, '2023-04-01', '12:00', 3);
INSERT IGNORE INTO reservations (id, store_id, user_id, checkin_date, checkin_time, number_of_people ) VALUES (4, 4, 1, '2023-04-01', '12:00', 4);
INSERT IGNORE INTO reservations (id, store_id, user_id, checkin_date, checkin_time, number_of_people ) VALUES (5, 5, 1, '2023-04-01', '12:00', 5);
INSERT IGNORE INTO reservations (id, store_id, user_id, checkin_date, checkin_time, number_of_people ) VALUES (6, 6, 2, '2023-04-01', '12:00', 6);
INSERT IGNORE INTO reservations (id, store_id, user_id, checkin_date, checkin_time, number_of_people ) VALUES (7, 7, 2, '2023-04-01', '12:00', 7);
INSERT IGNORE INTO reservations (id, store_id, user_id, checkin_date, checkin_time, number_of_people ) VALUES (8, 8, 2, '2023-04-01', '12:00', 8);
INSERT IGNORE INTO reservations (id, store_id, user_id, checkin_date, checkin_time, number_of_people ) VALUES (9, 9, 2, '2023-04-01', '12:00', 9);
INSERT IGNORE INTO reservations (id, store_id, user_id, checkin_date, checkin_time, number_of_people ) VALUES (10, 10, 2, '2023-04-01', '12:00', 1);
INSERT IGNORE INTO reservations (id, store_id, user_id, checkin_date, checkin_time, number_of_people ) VALUES (11, 11, 2, '2023-04-01', '12:00', 2);

-- favoritesテーブル
INSERT IGNORE INTO favorites (id, store_id, user_id) VALUES (1, 1, 1);
INSERT IGNORE INTO favorites (id, store_id, user_id) VALUES (2, 2, 1);
INSERT IGNORE INTO favorites (id, store_id, user_id) VALUES (3, 3, 1);
INSERT IGNORE INTO favorites (id, store_id, user_id) VALUES (4, 4, 1);
INSERT IGNORE INTO favorites (id, store_id, user_id) VALUES (5, 5, 1);
INSERT IGNORE INTO favorites (id, store_id, user_id) VALUES (6, 6, 3);
INSERT IGNORE INTO favorites (id, store_id, user_id) VALUES (7, 7, 3);
INSERT IGNORE INTO favorites (id, store_id, user_id) VALUES (8, 8, 3);
INSERT IGNORE INTO favorites (id, store_id, user_id) VALUES (9, 9, 3);
INSERT IGNORE INTO favorites (id, store_id, user_id) VALUES (10, 10, 3);

-- reviewsテーブル
INSERT IGNORE INTO reviews (id, store_id, user_id, score, content) VALUES (1, 1, 1, 5, 'おいしかったです');
INSERT IGNORE INTO reviews (id, store_id, user_id, score, content) VALUES (2, 1, 2, 4, 'おいしかったです');
INSERT IGNORE INTO reviews (id, store_id, user_id, score, content) VALUES (3, 1, 3, 4, 'おいしかったです');
INSERT IGNORE INTO reviews (id, store_id, user_id, score, content) VALUES (4, 1, 4, 5, 'おいしかったです');
INSERT IGNORE INTO reviews (id, store_id, user_id, score, content) VALUES (5, 1, 5, 4, 'おいしかったです');
INSERT IGNORE INTO reviews (id, store_id, user_id, score, content) VALUES (6, 1, 6, 5, 'おいしかったです');
INSERT IGNORE INTO reviews (id, store_id, user_id, score, content) VALUES (7, 1, 7, 4, 'おいしかったです');
INSERT IGNORE INTO reviews (id, store_id, user_id, score, content) VALUES (8, 1, 8, 3, 'おいしかったです');
INSERT IGNORE INTO reviews (id, store_id, user_id, score, content) VALUES (9, 1, 9, 4, 'おいしかったです');
INSERT IGNORE INTO reviews (id, store_id, user_id, score, content) VALUES (10, 1, 10, 5, 'おいしかったです');

-- companiesテーブル
INSERT IGNORE INTO companies (id, name, postal_code, address, representative, fouding_date, total_assets, business, number_of_employees) VALUES
(1, 'NAGOYAMESHI', '1234567', '東京都千代田区神田練塀町300 住友不動産秋葉原駅前ビル5F', '侍 太郎', '2012年3月4日', '100,000千円', '飲食店の情報提供サービス', '200名');
