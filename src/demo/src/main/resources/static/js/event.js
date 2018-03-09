function fieldChanged()
{
	var disabled = false;
	var name = document.getElementsByName("name")[0];
	var datelisttext = document.getElementsByName("datelisttext")[0];

	var memberselect = false;
	var target = document.getElementById('tablebody');
    if(0 < target.rows.length)
    {
    	memberselect = true;
    }

	if( name.value.length == 0 || datelisttext.value.length == 0 || memberselect == false )
	{
		disabled = true;
	}
	var set = document.getElementsByName("setevent")[0];
	set.disabled = disabled;
}



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

function addMember( dataIx ) {

//    var dataIx = targetEle.id;
	var baseTbl = document.getElementById('tablebodybase');
	var baseTr = baseTbl.getElementsByTagName('tr')[dataIx];
	var baseTeam = baseTr.cells[0];
	var baseName = baseTr.cells[1];

	var target = document.getElementById('tablebody');

    for( var i = 0; i < target.rows.length; i++ )
    {
        var cells = target.rows[ i ].cells;

        if( cells[0].innerHTML == baseTeam.innerHTML &&
        	cells[1].innerHTML == baseName.innerHTML )
        {
//			alert("該当メンバは追加済みです");
			return;
        }
    }

	var newTr = target.insertRow( -1 );

	var teamTd = newTr.insertCell( -1 );
	teamTd.appendChild(document.createTextNode(baseTeam.innerHTML));
	teamTd.className='team';

	var nameTd = newTr.insertCell( -1 );
	nameTd.appendChild(document.createTextNode(baseName.innerHTML));
	nameTd.className='name';

	var delJsTd = newTr.insertCell( -1 );
	delJsTd.className='btn';

	var delJs = document.createElement('input');
    delJs.type = 'button';
    delJs.value = "削除";
    delJs.id = target.rows.length -1;
    delJs.addEventListener('click', function(){
    	delMember(this.id);
    });
    delJs.style.cssText = "width: 100%";
    delJsTd.appendChild(delJs);

    var delJs = document.createElement('input');
    delJs.type = 'hidden';
    delJs.id  ='memberlist';
    delJs.name  = 'memberlist';
    delJs.value= baseName.innerHTML;
    delJsTd.appendChild(delJs);

//   fieldChanged()
}

function delMember( dataIx ) {

//	var dataIx = targetEle.id;

	var baseTbl = document.getElementById('tablebody');
	baseTbl.deleteRow(dataIx);

	for( var i = 0; i < baseTbl.rows.length; i++ )
    {
		var cells = baseTbl.rows[ i ].cells;
		cells[2].getElementsByTagName("input")[0].id = i;
    }

//	fieldChanged()
}


function addDate2( year, month, day ) {
	var y = document.getElementsByName( year )[0].value;
	var m = document.getElementsByName( month )[0].value;
	var d = document.getElementsByName( day )[0].value;
	if ( y && m && d ) {
		// 属性をセット
		var date = y + '/' + m + '/' + d;

		addDate(date);
	}
	else
	{
		alert("日付が不正");
	}
}

function addDate(date)
{
	// オプション要素を作成
	var option = document.createElement("option");
	option.value = date;
	option.text = date;
	// SELECT 要素を name で 取得
	var target = document.getElementsByName("datelist")[0];

	var dateSplit = date.split("/");

	var index = 0;
	for (; index < target.length; index++) {
		var targetSplit = target[index].value.split("/")

		if(dateSplit[0] < targetSplit[0])
		{
			break;
		}
		else if(dateSplit[0] == targetSplit[0])
		{
			if(dateSplit[1] < targetSplit[1])
			{
				break;
			}
			else if(dateSplit[1] == targetSplit[1])
			{
				if(dateSplit[2] < targetSplit[2])
				{
					break;
				}
				else if(dateSplit[2] == targetSplit[2])
				{
					//alert("該当日は追加済みです");
					return;
				}
			}
		}
	}

	// コレクションに追加
	target.add(option, index);
	// 追加した option を選択
	target.selectedIndex = index;

	// 隠しテキストボックスに日付文字列を追加
	var target = document.getElementsByName("datelisttext")[0];
	var str = "";
	if(target.value == "")
	{
		str = date;
	}
	else
	{
		var targetSplit = target.value.split(",")
		for (i = 0; i < index; i++) {
			str = str + targetSplit[i] + ",";
		}
		str = str + date;
		for (i = index; i < targetSplit.length; i++) {
			str = str + "," + targetSplit[i];
			str = str;
		}
	}
	target.value = str;

	// ボタンの活性/非活性を切り替え
	//fieldChanged();
}


function removeDate() {
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

		// ボタンの活性/非活性を切り替え
		//fieldChanged();
	}
}

function refine()
{
	var teamlist = document.getElementsByName("teamlist");

	var teamselect = false;
	for (i = 0; i < teamlist.length; i++) {
		if (teamlist[i].checked) {	// チェックボックス
			teamselect = true;
			break;
		}
	}

	var nameselect = false;
	var re = document.getElementsByName("search")[0];
	if(re.value != "")
	{
		nameselect = true;
	}

	if(teamselect || nameselect)
	{
		var target = document.getElementById('tablebodybase');
		for( var i = 0; i < target.rows.length; i++ )
		{
			var cells = target.rows[ i ].cells;

		    var team = cells[0].innerHTML;

		    var hit = false;
		    if(teamselect)
		    {
			    for (j = 0; j < teamlist.length; j++)
			    {
			    	if(team == teamlist[j].value)
			    	{
			    		if(teamlist[j].checked == false)
			    		{
			    			target.rows[ i ].style.display = "none";
				    		hit = true;
			    		}
			    		break;
			    	}
			    }
		    }
		    if(hit)
		    {
		    	continue;
		    }

		    if(nameselect)
		    {
			    var name = cells[1].innerHTML;
			    if( name.match(re.value) != null){
			    	target.rows[ i ].style.display = "";
			    }else{
			    	target.rows[ i ].style.display = "none";
				}
		    }
		    else
		    {
		    	target.rows[ i ].style.display = "";
		    }
		}
	}
	else
	{
		allDisplay();
	}
}

function refine2()
{
	var re = document.getElementsByName("search")[0];
	re.value = "";

	refine();
}

function allDisplay()
{
	var target = document.getElementById('tablebodybase');
	for( var i = 0; i < target.rows.length; i++ )
	{
		target.rows[ i ].style.display = "";
	}
}

function preset()
{
	var teamlist = document.getElementsByName("teamlistPreset");

	var teamselect = false;
	for (i = 0; i < teamlist.length; i++) {
		if (teamlist[i].checked) {	// チェックボックス
			teamselect = true;
			break;
		}
	}

	if(teamselect)
	{
		var target = document.getElementById('tablebodybase');
		for( var i = 0; i < target.rows.length; i++ )
		{
			var cells = target.rows[ i ].cells;

		    var team = cells[0].innerHTML;

		    var hit = false;
		    for (j = 0; j < teamlist.length; j++)
		    {
		    	if(team == teamlist[j].value)
		    	{
		    		if(teamlist[j].checked == true)
		    		{
		    			addMember( i );
			    		hit = true;
		    		}
		    		break;
		    	}
		    }
		}
	}
}



window.onload = function(){
	// ページ読み込み時に実行したい処理
	// ボタンの活性/非活性を切り替え
//	fieldChanged();

	// メンバリストの絞り込み
	refine();

	var id = document.getElementsByName("id")[0];
	if(id.value != "")
	{
		var target = document.getElementsByName('setevent')[0];
		target.value = "更新";
	}
}

