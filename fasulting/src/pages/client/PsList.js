import HospitalList from "../../components/HospitalList";
import MainCategoryList from "../../components/MainCategoryList";
import SubCategoryList from "../../components/SubCategoryList";

function PsList() {
  return (
    <div>
      <MainCategoryList />
      <SubCategoryList />
      <HospitalList />
    </div>
  );
}

export default PsList;
