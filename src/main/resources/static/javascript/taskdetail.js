document.addEventListener('DOMContentLoaded', () => {
	const displayBtnElements = document.querySelectorAll(".display-detail-btn"); // クラスセレクタを使用

	displayBtnElements.forEach(button => { // NodeListをループ処理
		button.addEventListener('click', function(event) {
			const taskId = this.getAttribute('data-task-id');
			if (taskId) {
				fetch(`/task/items?ID=${taskId}`)
					.then(res => res.text())
					.then(html => {
						const flagmentAreaElement = document.querySelector('#flagment-area');
						if (flagmentAreaElement) {
							flagmentAreaElement.innerHTML = html;
						} else {
							console.error("#flagment-area 要素が見つかりません。");
						}
					})
					.catch(error => {
						console.error("フラグメントの取得に失敗しました:", error);
					});
			} else {
				console.warn("taskIDが取得できませんでした。");
				return; // 処理を中断
			}
		});
	});
	
	const updateBtnElements = document.querySelector('#update');
	
	updateBtnElements.addEventListener('click' , event => {
		
	})
});