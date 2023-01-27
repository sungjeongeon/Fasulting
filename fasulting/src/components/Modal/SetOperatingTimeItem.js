import React from "react";
import styles from "./SetOperatingTimeItem.module.css";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import NativeSelect from "@mui/material/NativeSelect";
import Select from "@mui/material/Select";

function SetOperatingTimeItem({ day }) {
  const [age, setAge] = React.useState(10);

  const handleChange = (event) => {
    setAge(event.target.value);
  };
  return (
    <div className={styles.outerDiv}>
      <p>{day.name}</p>
      {/* 휴게없음 / 휴게있음 / 휴무일 선택 */}
      <FormControl>
        <Select
          labelId="demo-select-small"
          id="demo-select-small"
          value={age}
          onChange={handleChange}
        >
          <MenuItem value={10}>휴게없음</MenuItem>
          <MenuItem value={20}>휴게있음</MenuItem>
          <MenuItem value={30}>휴무일</MenuItem>
        </Select>
      </FormControl>

      {/* 시간 */}
      <FormControl>
        <NativeSelect
          defaultValue={0}
          inputProps={{
            name: "age",
            id: "uncontrolled-native",
          }}
        >
          <option value={0}>9:00</option>
          <option value={1}>9:30</option>
          <option value={2}>10:00</option>
          <option value={3}>10:30</option>
          <option value={4}>11:00</option>
          <option value={5}>11:30</option>
          <option value={6}>12:00</option>
          <option value={7}>12:30</option>
          <option value={8}>13:00</option>
          <option value={9}>13:30</option>
          <option value={10}>14:00</option>
          <option value={11}>14:30</option>
          <option value={12}>15:00</option>
          <option value={13}>15:30</option>
          <option value={14}>16:30</option>
          <option value={15}>16:00</option>
          <option value={16}>17:00</option>
          <option value={17}>17:30</option>
          <option value={18}>18:00</option>
          <option value={19}>18:30</option>
          <option value={20}>19:00</option>
        </NativeSelect>
      </FormControl>
      <p> ~ </p>
      {/* 분 */}
      <FormControl>
        <NativeSelect
          defaultValue={30}
          inputProps={{
            name: "age",
            id: "uncontrolled-native",
          }}
        >
          <option value={0}>9:00</option>
          <option value={1}>9:30</option>
          <option value={2}>10:00</option>
          <option value={3}>10:30</option>
          <option value={4}>11:00</option>
          <option value={5}>11:30</option>
          <option value={6}>12:00</option>
          <option value={7}>12:30</option>
          <option value={8}>13:00</option>
          <option value={9}>13:30</option>
          <option value={10}>14:00</option>
          <option value={11}>14:30</option>
          <option value={12}>15:00</option>
          <option value={13}>15:30</option>
          <option value={14}>16:30</option>
          <option value={15}>16:00</option>
          <option value={16}>17:00</option>
          <option value={17}>17:30</option>
          <option value={18}>18:00</option>
          <option value={19}>18:30</option>
          <option value={20}>19:00</option>
        </NativeSelect>
      </FormControl>
    </div>
  );
}

export default SetOperatingTimeItem;
