/**Grupo I Teste 21/22 SV*/

data class Bet(val key:List<Int>,val stars: List<Int>)
data class Prize(val num:Int,val equalKeys:Int,val equalsStars:Int)

val prizes = listOf( Prize(1,5,2), Prize(2,5,1), Prize(3,5,0), Prize(4,4,2), Prize(5,4,1),
    Prize(6,3,2), Prize(7,4,0), Prize(8,2,2), Prize(9,3,1), Prize(10,3,0),
    Prize(11,1,2), Prize(12,2,1), Prize(13,2,0) )

fun numberOfEquals(first:List<Int>,second:List<Int>):Int = first.count{it in second}

fun Bet.show(name:String)=println("$name: $key + $stars")

fun readInt(q:String):Int?{
    print("$q?")
    return readln().toIntOrNull()
}

//1
fun readValidNumber(quest:String,max:Int,exclude:List<Int>):Int{
    var num= readInt("$quest")
    while(true){
        if(num !in exclude && num in 1..max && num!=null){
            return num
        }
        else{num=readInt("$quest")}
    }
}
//2
fun readValidNumbers(name:String,size:Int,max:Int):List<Int>{
    var l = listOf<Int>()
    var order=1
    while(l.size<size){
        l=l+readValidNumber("$order,$name (1..$max)",max,l)
        order=order+1
    }
    return l
}
//3
fun generateRandomNumbers(size:Int,max:Int):List<Int>{
    var l= listOf<Int>()
    while(l.size<size){
        l=l+(1..max).random()
        l=l.distinct()
    }
    return l
}

//4
fun getBet(random:Boolean):Bet{
    return if(random){Bet(generateRandomNumbers(5,50),generateRandomNumbers(2,12))}
    else{Bet(readValidNumbers("Numero",5,50),readValidNumbers("Estrela",2,12))}
}

//5
fun Bet.getPrize(winning:Bet):Prize?{
    val x=numberOfEquals(key,winning.key)
    val y=numberOfEquals(stars,winning.stars)
    val z = prizes.filter {it.equalKeys==x && it.equalsStars==y}
    return if(z.isNotEmpty()){z[0]}
    else null
}

//6
fun main(){
    val a=readValidNumber("Quantas apostas?",5, emptyList())
    var bets= listOf<Bet>()
    repeat(a) {i->
        println("Aposta aleatoria?")
        bets= bets + getBet(readln()== "S")
    }
    val winning = getBet(true)
    winning.show("Chave:")
    bets.map{it.show("Aposta:")}
    val c= bets.map{it.getPrize(winning)}.filterNotNull()
    if(c.isNotEmpty()){println(c)}
    else{println("Sem prémio")}
}