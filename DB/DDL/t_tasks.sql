CREATE TABLE t_tasks (
    管理通番 SERIAL PRIMARY KEY,
    タスク管理ID CHAR(12) UNIQUE,
    タスク名 VARCHAR(20),
    ユーザ管理ID CHAR(12),
    開始日 DATE,
    期日 DATE,
    かかる時間 INT,
    内容 TEXT,
    コメント TEXT,
    優先度 CHAR(2),
    完了フラグ BOOLEAN,
    削除フラグ BOOLEAN
);

ALTER TABLE t_tasks
ADD CONSTRAINT fk_tasks_users
FOREIGN KEY (ユーザ管理ID)
REFERENCES t_users (ユーザ管理ID)
ON DELETE SET NULL
ON UPDATE CASCADE;

ALTER TABLE t_tasks
ADD CONSTRAINT fk_tasks_priorities
FOREIGN KEY (優先度)
REFERENCES t_priorities (優先度ID)
ON DELETE SET NULL
ON UPDATE CASCADE;

-- トリガー関数を作成
CREATE OR REPLACE FUNCTION generate_task_id()
RETURNS TRIGGER AS $$
BEGIN
    NEW.タスク管理ID := 'ISSUE_' || LPAD(NEW.管理通番::TEXT, 6, '0');
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- トリガーを作成し、INSERT時に実行されるように設定
CREATE TRIGGER set_task_id_on_insert
BEFORE INSERT ON t_tasks
FOR EACH ROW
EXECUTE FUNCTION generate_task_id();