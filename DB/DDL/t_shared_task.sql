CREATE TABLE t_shared_task (
    管理通番 SERIAL PRIMARY KEY,
    共有管理ID CHAR(12) UNIQUE,
    共有ユーザID CHAR(12),
    タスク管理ID CHAR(12)
);

ALTER TABLE t_shared_task
ADD CONSTRAINT fk_shared_task_users
FOREIGN KEY (共有ユーザID)
REFERENCES t_users (ユーザ管理ID)
ON DELETE SET NULL
ON UPDATE CASCADE;

ALTER TABLE t_shared_task
ADD CONSTRAINT fk_shared_task_tasks
FOREIGN KEY (タスク管理ID)
REFERENCES t_tasks (タスク管理ID)
ON DELETE SET NULL
ON UPDATE CASCADE;

-- トリガー関数を作成
CREATE OR REPLACE FUNCTION generate_share_id()
RETURNS TRIGGER AS $$
BEGIN
    NEW.共有管理ID := 'SHARE_' || LPAD(NEW.管理通番::TEXT, 6, '0');
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- トリガーを作成し、INSERT時に実行されるように設定
CREATE TRIGGER set_share_id_on_insert
BEFORE INSERT ON t_shared_task
FOR EACH ROW
EXECUTE FUNCTION generate_share_id();