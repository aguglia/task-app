document.addEventListener('DOMContentLoaded', () => {
	const flagmentAreaElement = document.querySelector('#flagment-area'); // 静的な親要素

	if (flagmentAreaElement) {
		flagmentAreaElement.addEventListener('click', event => {
			const target = event.target;
			if (target && target.id === 'update-button') {
				console.log('Delegated button clicked!');
				const taskId = target.getAttribute('data-task-id');
				const taskNameInput = flagmentAreaElement.querySelector('#taskname'); // 例
				const contentTextarea = flagmentAreaElement.querySelector('#content'); // 確認されたIDを使用
				const startDateInput = flagmentAreaElement.querySelector('#startdate'); // 例
				const deadlineDateInput = flagmentAreaElement.querySelector('#deadlinedate'); // 例 (必要に応じて調整)
				const requiredTimeInput = flagmentAreaElement.querySelector('#requiredtime'); // 例
				const commentTextarea = flagmentAreaElement.querySelector('#coment'); // 例
				const prioritySpan = flagmentAreaElement.querySelector('span[class*="priority-"]'); // クラス名で特定する例

				if (!taskId) {
					console.error('Task ID is missing!');
					return;
				}

				const updatedTaskData = {
					ID: taskId,
					taskname: taskNameInput ? taskNameInput.value : null,
					content: contentTextarea ? contentTextarea.value : null,
					startdate: startDateInput ? startDateInput.value : null,
					deadlinedate: deadlineDateInput ? deadlineDateInput.value : null,
					Requiredtime: requiredTimeInput ? requiredTimeInput.value : null,
					coment: commentTextarea ? commentTextarea.value : null,
					priority: prioritySpan ? prioritySpan.textContent : null
				};

				const queryString = new URLSearchParams();
				queryString.append('ID', taskId);
				queryString.append('taskname', updatedTaskData.taskname);
				queryString.append('content', updatedTaskData.content);
				queryString.append('startdate', updatedTaskData.startdate);
				queryString.append('deadlinedate', updatedTaskData.deadlinedate);
				queryString.append('Requiredtime', updatedTaskData.Requiredtime);
				queryString.append('coment', updatedTaskData.coment);
				queryString.append('priority', updatedTaskData.priority);

				fetch(`/tasks/update?${queryString.toString()}`) // URL パラメータを構築
					.then(res => res.text())
					.then(html => {
						//const flagmentAreaElement = document.querySelector('#flagment-area');
						if (flagmentAreaElement) {
							flagmentAreaElement.innerHTML = html;
						} else {
							console.error("#flagment-area 要素が見つかりません。");
						}
					})
					.catch(error => {
						console.error("フラグメントの取得に失敗しました:", error);
					});
			}
			if (target && target.id === 'modal-close-button') {
				const modalElement = document.querySelector('#modal');
				modalElement.classList.add('hide');
				modalElement.classList.remove('show');

				// アニメーション終了後に非表示に
				setTimeout(() => {
					const overlayElement = document.querySelector('#overlay');
					overlayElement.classList.add('hidden');
				}, 500);
			}
		});
	}
});