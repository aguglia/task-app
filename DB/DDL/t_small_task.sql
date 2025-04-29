CREATE TABLE t_small_task (
    管理通番 SERIAL PRIMARY KEY,
    小タスクID CHAR(12) UNIQUE,
    タスク管理ID CHAR(12),
    内容 VARCHAR(20),
    完了フラグ BOOLEAN,
    削除フラグ BOOLEAN
);

ALTER TABLE t_small_task
ADD CONSTRAINT fk_small_task_tasks
FOREIGN KEY (タスク管理ID)
REFERENCES t_tasks (タスク管理ID)
ON DELETE SET NULL
ON UPDATE CASCADE;

-- トリガー関数を作成
CREATE OR REPLACE FUNCTION generate_small_id()
RETURNS TRIGGER AS $$
BEGIN
    NEW.小タスクID := 'SMALL_' || LPAD(NEW.管理通番::TEXT, 6, '0');
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- トリガーを作成し、INSERT時に実行されるように設定
CREATE TRIGGER set_small_id_on_insert
BEFORE INSERT ON t_small_task
FOR EACH ROW
EXECUTE FUNCTION generate_small_id();
