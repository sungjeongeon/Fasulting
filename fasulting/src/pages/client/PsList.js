import HospitalList from "../../components/List/HospitalList";
import MainCategoryList from "../../components/Category/MainCategoryList";
import SubCategoryList from "../../components/Category/SubCategoryList";
import { useState } from "react";
import { useEffect } from "react";
import axiosApi from "../../api/axiosApi";
import { useParams } from "react-router-dom";

function PsList() {
  const param = useParams();
  const [subcategory, setSubcategory] = useState([]);
  const [selectedSub, setSelectedSub] = useState([]);
  useEffect(() => {
    axiosApi.get(`/main/sub/${param.seq}`).then((res) => {
      //console.log(res.data);
      setSubcategory(res.data.responseObj);
      setSelectedSub([]); //선택된 배열 초기화
    });
  }, [param]);

  // 해당 sub값이 이미 배열에 있으면 빼고, 없으면 더한다.
  const selectSub = (sub) => {
    if (selectedSub.includes(sub)) {
      // sub값 이미 배열에 있으면 (제거){}
      setSelectedSub((current) => current.filter((resist) => resist !== sub));
    } else {
      setSelectedSub((current) => [...current, sub]);
    }
  };
  return (
    <div>
      <MainCategoryList />
      <SubCategoryList
        subcategory={subcategory}
        selectedSub={selectedSub}
        selectSub={selectSub}
      />
      <HospitalList selectSub={selectSub} />
    </div>
  );
}

export default PsList;
