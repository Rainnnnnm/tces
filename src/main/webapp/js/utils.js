/**
 * 更新表格序号
 * @param trList tr元素集合
 */
function reIndex(trList) {

    if(trList == null){
        trList = $("table>tbody tr");
    }

    for (var i = 0; i < trList.length; i++){
        trList[i].firstElementChild.innerText = i + 1;
    }
    // console.log(indexObj);
}