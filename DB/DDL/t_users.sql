CREATE TABLE t_users (
    管理通番 SERIAL PRIMARY KEY,
    ユーザ管理ID CHAR(12) UNIQUE,
    ユーザ名 VARCHAR(10),
    パスワード VARCHAR(100),
    メアド VARCHAR(50) UNIQUE
);

-- トリガー関数を作成
CREATE OR REPLACE FUNCTION generate_user_id()
RETURNS TRIGGER AS $$
BEGIN
    NEW.ユーザ管理ID := 'USERS_' || LPAD(NEW.管理通番::TEXT, 6, '0');
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- トリガーを作成し、INSERT時に実行されるように設定
CREATE TRIGGER set_user_id_on_insert
BEFORE INSERT ON t_users
FOR EACH ROW
EXECUTE FUNCTION generate_user_id();