function dateValidation( element, year, month, day ) {
	var y = document.getElementsByName( year )[0].value;
	var m = document.getElementsByName( month )[0].value;
	var d = document.getElementsByName( day )[0].value;
	if ( y && m && d ) {
		var ds = new Date( y, m-1, d );
		if ( ds.getFullYear() != y || ds.getMonth() != m-1 || ds.getDate() != d ) {
			alert("日付がおかしいよ！！");
			return element.value = '';
		}
	}
}

//(function() {
//	  'use strict';
//
//	  /*
//	    今日の日付データを変数todayに格納
//	   */
//	  var optionLoop, this_day, this_month, this_year, today;
//	  today = new Date();
//	  this_year = today.getFullYear();
//	  this_month = today.getMonth() + 1;
//	  this_day = today.getDate();
//
//	  /*
//	    ループ処理（スタート数字、終了数字、表示id名、デフォルト数字）
//	   */
//	  optionLoop = function(start, end, id) {
//	    var i, opt;
//
//	    alert("start:"+start);
//	    alert("end:"+end);
//	    alert("id:"+id);
//
//	    opt = '<option value=""></option>';
//	    for (i = start; i <= end ; i++) {
////	      if (i === this_day) {
////	        opt += "<option value='" + i + "' selected>" + i + "</option>";
////	      } else {
//	        opt += '<option value="' + i + '">' + i + '</option>';
////	      }
//	    }
//	    alert(opt);
//	    return document.getElementById(id).innerHTML = opt;
//	  };
//
//
//	  /*
//	    関数設定（スタート数字[必須]、終了数字[必須]、表示id名[省略可能]、デフォルト数字[省略可能]）
//	   */
//	  optionLoop(this_year, this_year+20, 'id_year');
////	  optionLoop(1, 12, 'id_month', this_month);
////	  optionLoop(1, 31, 'id_day', this_day);
//})();



function addOption( year, month, day ) {
	var y = document.getElementsByName( year )[0].value;
	var m = document.getElementsByName( month )[0].value;
	var d = document.getElementsByName( day )[0].value;
	if ( y && m && d ) {
		// オプション要素を作成
		var option = document.createElement("option");
		// 属性をセット
		var date = y + '/' + m + '/' + d;
		option.value = date;
		option.text = date;
		// SELECT 要素を name で 取得
		var target = document.getElementsByName("datelist")[0];

		for (i = 0; i < target.length; i++) {
			if (target[i].value == date) {
				alert("該当日は追加済みです");
				return;
			}
		}

		// コレクションに追加
		target.add(option);
		// 追加した option を選択
		target.selectedIndex = target.length-1;

		// 隠しテキストボックスに日付文字列を追加
		var target = document.getElementsByName("datelisttext")[0];
		if(target.value == "")
		{
			target.value = date;
		}
		else
		{
			target.value = target.value + "," + date;
		}
	}
	else
	{
		alert("日付が不正");
	}
}

function removeOption() {
	// SELECT 要素を name で 取得
	var target = document.getElementsByName("datelist")[0];
	// 選択されている index を取得
	var index = target.selectedIndex;
	// -1 の場合何も選択されていない
	if ( index != -1 ) {
		// 選択されている index で削除
		target.remove(index)

		// 隠しテキストボックスから日付文字列を削除
		var target = document.getElementsByName("datelisttext")[0];
		var str = target.value;
		var date = str.split(",");
		var strNew = "";
		for (i = 0; i < date.length; i++) {
			if (i != index) {
				if(strNew == "")
				{
					strNew = date[i];
				}
				else
				{
					strNew = strNew + "," + date[i];
				}
			}
		}
		target.value = strNew;
	}
}

