import ConsultingCard from "./ConsultingCard";

function ConsultingCardList() {
    const consulting = [
        {
            user : {
                user_id : "id1"	
            },
            ps : {
                ps_id : "psid1",
            },
            sub_category : {
                sub_category_name : "쌍커풀"
            },
            reservation : {
                    calender_id : "2020.12.30 10시 25분",
            }
	    },
        {
            user : {
                user_id : "id2"	
            },
            ps : {
                ps_id : "psid2",
            },
            sub_category : {
                sub_category_name : "콧볼축소"
            },
            reservation : {
                    calender_id : "2022.01.31 12시 30분",
            }
	    },

    ]
    return (
        <div>
            {consulting.map((consult) => (
                <ConsultingCard key={consult.ps.ps_id.toString()} 
                    consult={consult}/>
            ))}
        </div>
    )
}
export default ConsultingCardList;